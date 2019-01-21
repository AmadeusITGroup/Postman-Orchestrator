package postman.orchestrator.common.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import postman.orchestrator.common.FolderStructure;
import postman.orchestrator.common.TestDescriptor;
import postman.orchestrator.models.FolderItem;
import postman.orchestrator.models.PostmanCollection;
import postman.orchestrator.models.PostmanCollectionItem;
import postman.orchestrator.serializers.CollectionItemListSerializer;
import postman.orchestrator.serializers.CollectionItemSerializer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This service is responsible of Postman collection manipulation.
 * It can read, save and also combine collections
 * @author Anthony Todisco
 * @version 1.0
 * @since PostmanOrchestrator 1.0
 */
public class CollectionService {

  private Gson gson;
  private final int MAX_SEARCH_DEPTH = 10;

  public CollectionService() {
    GsonBuilder builder = new GsonBuilder();
    builder.registerTypeAdapter(new TypeToken<List<PostmanCollectionItem>>(){}.getType(), new CollectionItemListSerializer());
    builder.registerTypeAdapter(PostmanCollectionItem.class, new CollectionItemSerializer());
    this.gson = builder.create();
  }

  public FolderStructure getStructure(String rootPath, List<TestDescriptor> descriptors) {
    Path root = Paths.get(rootPath);
    FolderStructure rootStruct = new FolderStructure(root.getFileName().toString());
    descriptors.stream().forEach(descriptor -> {
      FolderStructure res = getFolderStructure(root, descriptor);
      rootStruct.combine(res);
    });
    return rootStruct;
  }

  public PostmanCollection combineCollections(String collectionName, FolderStructure structure) {
    PostmanCollection result = new PostmanCollection(collectionName);

    result.setItems(structure.getCollections().stream()
            .map(c -> new FolderItem(c))
            .collect(Collectors.toList())
    );
    structure.getFolders().stream().forEach(f -> {
      addFolderStructToCollectionItems(f, result.getItems());
    });

    return result;
  }

  public void saveCollection(String filePath, PostmanCollection collection) {
    try (Writer writer = new FileWriter(filePath)) {
      this.gson.toJson(collection, writer);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private FolderStructure getFolderStructure(Path root, TestDescriptor descriptor) {
    Optional<File> file = findCollectionFile(root, descriptor.getCollectionName());
    if(file.isPresent()) {
      Path filePath =  file.get().toPath();
      return getFolderStructure(root, filePath, descriptor);
    }
    return null;
  }

  private FolderStructure getFolderStructure(Path root, Path filePath, TestDescriptor descriptor) {
    FolderStructure struct = new FolderStructure(root.getFileName().toString());

    Path relativePath = root.relativize(filePath.getParent());

    if(StringUtils.isBlank(relativePath.toString())) {
      Optional<PostmanCollection> collection = getCollection(filePath);
      if(collection.isPresent()) {
        if(descriptor.loadFullCollection()) {
          struct.getCollections().add(collection.get());
        } else {
          struct.getCollections().add(filterCollection(collection.get(), descriptor.getFoldersToLoad()));
        }
      }
    } else {
      root = root.resolve(relativePath.getName(0));
      FolderStructure child = getFolderStructure(root, filePath, descriptor);
      struct.getFolders().add(child);
    }
    return struct;
  }

  private PostmanCollection filterCollection(PostmanCollection postmanCollection, List<String> foldersToLoad) {
    PostmanCollection collection = new PostmanCollection();
    collection.setInfo(postmanCollection.getInfo());
    collection.setEvents(postmanCollection.getEvents());

    collection.setItems(postmanCollection.getItems().stream()
                          .filter(item -> item instanceof FolderItem && foldersToLoad.contains(item.getName()))
                          .collect(Collectors.toList())
    );

    return collection;
  }

  private Optional<PostmanCollection> getCollection(Path filePath) {
    PostmanCollection collection = null;
    try {
      String content = new String(Files.readAllBytes(filePath));
      collection = gson.fromJson(content, PostmanCollection.class);
    }
    catch (Exception ge) {
      System.out.printf("Error on file: %s%n%n", filePath.toString());
      ge.printStackTrace();
    }
    return Optional.ofNullable(collection);
  }

  private void addFolderStructToCollectionItems(FolderStructure folder, List<PostmanCollectionItem> items) {
    FolderItem item = new FolderItem();
    item.setName(folder.getName());
    item.setItems(folder.getCollections().stream()
            .map(c -> new FolderItem(c))
            .collect(Collectors.toList())
    );

    folder.getFolders().stream().forEach(f -> addFolderStructToCollectionItems(f, item.getItems()));
    items.add(item);
  }

  private Optional<File> findCollectionFile(Path start, String fileName) {
    try (final Stream<Path> files = Files.find(start, MAX_SEARCH_DEPTH, (path, attribute) ->
            path.toFile().isFile() && path.toString().endsWith(".json") &&
                    path.getFileName().toString().equalsIgnoreCase(fileName)
    )) {
      return files.map(Path::toFile).findFirst();
    }
    catch (IOException ioe){
      ioe.printStackTrace();
      return Optional.empty();
    }
  }

  public List<TestDescriptor> findCollectionFiles(Path start) {
    try (final Stream<Path> pathStream = Files.walk(start, MAX_SEARCH_DEPTH)) {
      return pathStream.filter(p -> !p.toFile().isDirectory() && p.toFile().getAbsolutePath().endsWith(".json"))
              .map(Path::toString)
              .map(TestDescriptor::new)
              .collect(Collectors.toList());
    } catch (final IOException ioe) {
      ioe.printStackTrace();
      return new ArrayList<>();
    }
  }
}
