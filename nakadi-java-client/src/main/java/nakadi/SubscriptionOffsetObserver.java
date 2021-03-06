package nakadi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

class SubscriptionOffsetObserver implements StreamOffsetObserver {

  private static final Logger logger = LoggerFactory.getLogger(NakadiClient.class.getSimpleName());

  private final SubscriptionOffsetCheckpointer checkpointer;

  public SubscriptionOffsetObserver(SubscriptionOffsetCheckpointer checkpointer) {
    this.checkpointer = checkpointer;
  }

  @Override public void onNext(StreamCursorContext context) {
    try {
      MDC.put("cursor_context", context.toString());
      logger.debug("subscription_checkpoint starting checkpoint {}", context);
      checkpoint(context);
    } finally {
      MDC.remove("cursor_context");
    }
  }

  private void checkpoint(StreamCursorContext context) {
    checkpointer().checkpoint(context);
  }

  @VisibleForTesting
  SubscriptionOffsetCheckpointer checkpointer() {
    return checkpointer;
  }
}
