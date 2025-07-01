package br.com.iuriraredu.utils;

import br.com.iuriraredu.config.ApiConfig;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utilitário para geração de relatórios de execução de testes automatizados.
 * Implementa extensões do JUnit 5 para criar, registrar e finalizar relatórios com o ExtentReports.
 * Permite logar informações, status de sucesso e falha de cada teste.
 */
public class ReportUtils implements BeforeAllCallback, AfterAllCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback{
    private static ExtentReports extent;
    private static ExtentTest test;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    /**
     * Inicializa o relatório antes da execução de todos os testes.
     * Cria um novo arquivo de relatório com timestamp no nome.
     */
    @Override
    public void beforeAll(ExtensionContext context){
        if (extent == null){
            String reportName = ApiConfig.getReportName() + dtf.format(LocalDateTime.now()) + ".html";
            ExtentSparkReporter spark = new ExtentSparkReporter(ApiConfig.getReportPath() + reportName);
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
    }

    /**
     * Finaliza e grava o relatório após a execução de todos os testes.
     */
    @Override
    public void afterAll(ExtensionContext context){
        if (extent != null){
            extent.flush();
        }
    }

    /**
     * Cria uma nova entrada de teste no relatório antes da execução de cada teste.
     * @param context Contexto do teste.
     */
    @Override
    public void beforeTestExecution(ExtensionContext context){
        test = extent.createTest(context.getDisplayName());
    }

    /**
     * Registra o resultado do teste (sucesso ou falha) no relatório após a execução de cada teste.
     * @param context Contexto do teste.
     */
    @Override
    public void afterTestExecution(ExtensionContext context){
        if (context.getExecutionException().isPresent()){
            test.log(Status.FAIL, "Test failed: " + context.getExecutionException().get().getMessage());
        } else {
            test.log(Status.PASS, "Test passed");
        }
    }

    /**
     * Adiciona uma mensagem informativa ao relatório do teste em execução.
     * @param message Mensagem a ser registrada.
     */
    public static void logInfo(String message){
        test.log(Status.INFO, message);
    }
}
