package fr.eletutour.kataSG.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StatementPrinter {

    public void print(List<StatementLine> statement) {
        if (statement.isEmpty()){
            return;
        }
        System.out.println("DATE | OPERATION | AMOUNT | BALANCE");
        for (StatementLine statementLine : statement) {
            printLine(statementLine);
        }

    }

    private void printLine(StatementLine statementLine) {
        System.out.println(formatDate(statementLine.getOperation().getCreatedOn())
         + "|"
         + statementLine.getOperation().getTypeOperation().name()
         + "|"
         + statementLine.getOperation().getAmount().getAmount()
         + "|"
         + statementLine.getBalance().getAmount());
    }

    private String formatDate(Date dateToFormat){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(dateToFormat);
    }
}
