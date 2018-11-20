package com.hq.bpmn.unify.service.impl.simple;

import com.hq.bpmn.common.bean.ProcessResult;
import com.hq.bpmn.common.util.WfBeanFactory;
import com.hq.bpmn.exception.BpmnException;
import com.hq.bpmn.processinstance.bean.BpmnBatchCompleteResult;
import com.hq.bpmn.processinstance.bean.BpmnExecutionInfo;
import com.hq.bpmn.processinstance.bean.BpmnTicket;
import com.hq.bpmn.processinstance.service.InformationDeskService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class BpmnServiceSimpleImplTest {




    @Test
    public void batchCreateBpmnProcessWithNextTask() throws BpmnException {


        String[] url = new String[]{"application-context-wf_test.xml"};
            @SuppressWarnings("resource")
            ApplicationContext ctx = new ClassPathXmlApplicationContext(url);
        System.out.println(ctx);
        BpmnServiceSimpleImpl bpmnServiceSimpleImpl = (BpmnServiceSimpleImpl) ctx.getBean("bpmnServiceSimpleImpl");
        System.out.println(bpmnServiceSimpleImpl);
            String userId="FBAFBF5A43E8A70004A414E47F3CDE5A";
            String bpmnType="0102";
            String ticketIds="2018110611";
            String variable="";
        final ProcessResult<List<BpmnTicket>> listProcessResult = bpmnServiceSimpleImpl.batchCreateBpmnProcessWithNextTask(userId, bpmnType, ticketIds, variable);


    }

    @Test
    public void batchCompleteBpmnTaskWithNextTask_Reconstruction() {
        String[] url = new String[]{"application-context-wf_test.xml"};
        @SuppressWarnings("resource")
        ApplicationContext ctx = new ClassPathXmlApplicationContext(url);
        System.out.println(ctx);
        BpmnServiceSimpleImpl bpmnServiceSimpleImpl = (BpmnServiceSimpleImpl) ctx.getBean("bpmnServiceSimpleImpl");
        System.out.println(bpmnServiceSimpleImpl);
        String userId="51ACE7408A7F3071486A16C381E5233F";
//		String userId="7C5F5B4737051F3DFD0215FB0371B61E";
//		String userId="b";
//		String userId="D3D1AD90731114A2E715CF027B14CB6B";
//		String userId="66CC324DE77AB49BBD6545FE0A58098D";
//		String userId="gonzo";
//		String userId="a";
//		String userId="k";
//		66CC324DE77AB49BBD6545FE0A58098D    测试角色中央本级
//		String ticketId="20181017003";
        String bpmnBatchTransition = "audit";
//		String variable="";
        String bpmnType="0102";
//		String bpmnType="lwcx";
        String ticketId="2018110611";
//		String bpmnBatchTransition = "pcl_key";
        String variable="";
//		String variable="[{'name':'money','type':'string','value':'100000.0'}]";

//		String userId="66A31322D87CF43BCF5CE8EEC532FF8C";
//		String bpmnType="039001";
//		String ticketId="1FA3F34701C16ABCA26AD40ECFF0EB8F,698FFFF539B1EFFBD853035DBD2AD257";
//		String bpmnBatchTransition = "sendaudit";
//		String variable=null;


        try
        {
            ProcessResult<List<BpmnBatchCompleteResult>> pr = bpmnServiceSimpleImpl.batchCompleteBpmnTaskWithNextTask_Reconstruction(userId, bpmnType, ticketId, bpmnBatchTransition, variable);
            List<BpmnBatchCompleteResult> list=  pr.getResult();
            System.out.println(" pr.getCode():" + pr.getCode());
            System.out.println(" pr.getMessage():" + pr.getMessage());
            System.out.println(" pr.isSuccess():" + pr.isSuccess());
            for (BpmnBatchCompleteResult bpmnBatchCompleteResult : list) {
                System.out.println("bpmnBatchCompleteResult:" + bpmnBatchCompleteResult.toString());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("---------------OK-------------------");


    }
}