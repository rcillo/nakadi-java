package nakadi;

import java.util.List;

/**
 * Represents the partitions for an event type.
 */
public class PartitionCollection extends ResourceCollection<Partition> {

  private final EventTypeResourceReal eventTypeResource;

  PartitionCollection(List<Partition> items, List<ResourceLink> links,
      EventTypeResourceReal eventTypeResource, NakadiClient client) {
    super(items, links, client);
    this.eventTypeResource = eventTypeResource;
  }

  public ResourceCollection<Partition> fetchPage(String url) {
    return eventTypeResource.loadPartitionPage(url);
  }
}
