package eu.glowacki.tpo.jms.messages;

public final class MessageProcessorFactory {

    private static final IMessageProcessor ARITHMETIC_REQUEST = null;
    private static final IMessageProcessor RANDOM_REQUEST = null;

    private MessageProcessorFactory() {
    }

    public static IMessageProcessor processor(IRequest request) {
        return null;
    }
}
