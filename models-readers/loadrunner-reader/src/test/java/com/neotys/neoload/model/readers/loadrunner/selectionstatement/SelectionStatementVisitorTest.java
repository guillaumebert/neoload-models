package com.neotys.neoload.model.readers.loadrunner.selectionstatement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.google.common.base.Charsets;
import com.neotys.neoload.model.core.Element;
import com.neotys.neoload.model.listener.TestEventListener;
import com.neotys.neoload.model.readers.loadrunner.LoadRunnerReader;
import com.neotys.neoload.model.repository.Condition;
import com.neotys.neoload.model.repository.Conditions.MatchType;
import com.neotys.neoload.model.repository.Container;
import com.neotys.neoload.model.repository.IfThenElse;
@SuppressWarnings("squid:S2699")
public class SelectionStatementVisitorTest {

	@Test
	public void testVisitSelectionstatement(){
		final String folder = new File(this.getClass().getResource("ActionSelectionStatement.c").getFile()).getParent();
        final LoadRunnerReader reader = new LoadRunnerReader(new TestEventListener(), folder, "", "");
        try(InputStream targetStream = this.getClass().getResourceAsStream("ActionSelectionStatement.c")) {
            Container container = reader.parseCppFile("{", "}", targetStream, "MyContainer", Charsets.UTF_8);
            assertThat(container).isNotNull();
            assertThat(container.getChilds().size()).isEqualTo(2);
            final Element isObjectAvailable = container.getChilds().get(0);
            assertThat(isObjectAvailable.getName()).isEqualTo("isObjectAvailable");
            final IfThenElse ifThenElse1 = (IfThenElse) container.getChilds().get(1);
            assertThat(ifThenElse1.getName()).isEqualTo("condition");
            assertThat(ifThenElse1.getConditions().getMatchType()).isEqualTo(MatchType.ANY);
            assertThat(ifThenElse1.getConditions().getConditions().size()).isEqualTo(1);
            assertThat(ifThenElse1.getConditions().getConditions().get(0).getOperand1()).isEqualTo("${sapgui_is_object_available_1}");
            assertThat(ifThenElse1.getConditions().getConditions().get(0).getOperator()).isEqualTo(Condition.Operator.EQUALS);
            assertThat(ifThenElse1.getConditions().getConditions().get(0).getOperand2()).isEqualTo("true");
            final Container thenContainer = ifThenElse1.getThen();            
            assertThat(thenContainer.getName()).isEqualTo("Then");
            assertThat(thenContainer.getChilds().size()).isEqualTo(2);
            assertThat(thenContainer.getChilds().get(0).getName()).isEqualTo("setOkCode");
            assertThat(thenContainer.getChilds().get(1).getName()).isEqualTo("setOkCode_1");
            final Container elseContainer = ifThenElse1.getElse();
            assertThat(elseContainer.getName()).isEqualTo("Else");
            assertThat(elseContainer.getChilds().size()).isEqualTo(3);
            assertThat(elseContainer.getChilds().get(0).getName()).isEqualTo("setOkCode");
            assertThat(elseContainer.getChilds().get(1).getName()).isEqualTo("isObjectAvailable");
            assertThat(elseContainer.getChilds().get(2).getName()).isEqualTo("condition");
            final IfThenElse ifThenElse2 = (IfThenElse) elseContainer.getChilds().get(2);
            assertThat(ifThenElse2.getConditions().getMatchType()).isEqualTo(MatchType.ANY);
            assertThat(ifThenElse2.getConditions().getConditions().size()).isEqualTo(1);
            assertThat(ifThenElse2.getConditions().getConditions().get(0).getOperand1()).isEqualTo("${sapgui_is_object_available_2}");
            assertThat(ifThenElse2.getConditions().getConditions().get(0).getOperator()).isEqualTo(Condition.Operator.EQUALS);
            assertThat(ifThenElse2.getConditions().getConditions().get(0).getOperand2()).isEqualTo("true");
            final Container thenContainer2 = ifThenElse2.getThen();
            assertThat(thenContainer2.getName()).isEqualTo("Then");   
            assertThat(thenContainer2.getChilds().size()).isEqualTo(1);
            assertThat(thenContainer2.getChilds().get(0).getName()).isEqualTo("setOkCode");            
            final Container elseContainer2 = ifThenElse2.getElse();
            assertThat(elseContainer2.getName()).isEqualTo("Else");
            assertThat(elseContainer2.getChilds().size()).isEqualTo(0);            
        }catch(IOException e) {
            fail("Error reading test stream", e);
        }
	}
}