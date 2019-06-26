package rpn.consumer.operator;

import rpn.bus.Bus;
import rpn.consumer.Consumer;
import rpn.message.Message;
import rpn.message.OperationMessage;
import rpn.message.OperatorMessage;

public class Drop implements Consumer {


    private final Bus bus;
    public Drop(Bus bus) {
        this.bus = bus;
    }

    @Override
    public void receive(Message message) {
        OperationMessage operationMessage = (OperationMessage) message;
        if(!operationMessage.stackNumbers.empty()) {
            operationMessage.stackNumbers.pop();
            bus.publish(new OperatorMessage(operationMessage.stackNumbers, operationMessage.expressionId));
        }
    }
}
