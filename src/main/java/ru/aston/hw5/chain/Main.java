package ru.aston.hw5.chain;

public class Main {

    public static void main(String[] args) {
        ReturnHandler statusCheck = new OrderStatusCheck();
        ReturnHandler periodCheck = new ReturnPeriodCheck();
        ReturnHandler processReturn = new ProcessReturn();

        statusCheck.setNext(periodCheck);
        periodCheck.setNext(processReturn);

        ReturnRequest request1 = new ReturnRequest("123", true, 10);
        statusCheck.handleReturn(request1);

        ReturnRequest request2 = new ReturnRequest("456", false, 5);
        statusCheck.handleReturn(request2);
    }
}