package rpn.consumer.operator;

import rpn.bus.Bus;
import rpn.consumer.Consumer;
import rpn.message.Message;
import rpn.message.OperationMessage;
import rpn.message.OperatorMessage;

public class Time implements Consumer {
    private final Bus bus;
    public Time(Bus bus) {
        this.bus = bus;
    }
    @Override
    public void receive(Message message) {
        OperationMessage operationMessage = (OperationMessage) message;
        if(operationMessage.stackNumbers.empty()) {
            double exponent = operationMessage.stackNumbers.pop();
            double res = Math.pow(operationMessage.stackNumbers.pop(), exponent);
            operationMessage.stackNumbers.push(res);
            bus.publish(new OperatorMessage(operationMessage.stackNumbers, operationMessage.expressionId));
        }
    }
}
