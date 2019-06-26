package rpn.consumer.operator;

import rpn.bus.Bus;
import rpn.consumer.Consumer;
import rpn.message.Message;
import rpn.message.OperationMessage;
import rpn.message.OperatorMessage;

public class Swap implements Consumer {
    private final Bus bus;
    public Swap(Bus bus) {
        this.bus = bus;
    }
    @Override
    public void receive(Message message) {
        OperationMessage operationMessage = (OperationMessage) message;
        if(!operationMessage.stackNumbers.empty()) {
            double fistNum = operationMessage.stackNumbers.pop();
            double secondNum = operationMessage.stackNumbers.pop();
            operationMessage.stackNumbers.push(fistNum);
            operationMessage.stackNumbers.push(secondNum);
            bus.publish(new OperatorMessage(operationMessage.stackNumbers, operationMessage.expressionId));
        }
    }
}
