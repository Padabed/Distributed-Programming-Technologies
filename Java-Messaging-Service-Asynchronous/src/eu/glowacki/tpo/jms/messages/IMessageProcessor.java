package eu.glowacki.tpo.jms.messages;

public interface IMessageProcessor {

    IResponse process(IRequest request);
}
