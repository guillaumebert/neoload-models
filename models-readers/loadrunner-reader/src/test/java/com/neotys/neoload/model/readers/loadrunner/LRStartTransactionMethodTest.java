package com.neotys.neoload.model.readers.loadrunner;
import static com.neotys.neoload.model.readers.loadrunner.LoadRunnerReaderTestUtil.LOAD_RUNNER_READER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.google.common.base.Charsets;
import com.neotys.neoload.model.repository.Container;
@SuppressWarnings("squid:S2699")
public class LRStartTransactionMethodTest {

	@Test
    public void transactionsReaderTest() {    	
        try(InputStream targetStream = this.getClass().getResourceAsStream("ActionTransaction.c")) {
            Container container = LOAD_RUNNER_READER.parseCppFile("{", "}", targetStream, "MyContainer", Charsets.UTF_8);
            assertThat(container).isNotNull();
            assertThat(container.getChilds().size()).isEqualTo(2);
            assertThat(container.getChilds().get(0).getName()).isEqualTo("level_1");
            assertThat(container.getChilds().get(1).getName()).isEqualTo("page#3");
            assertThat(((Container)container.getChilds().get(0)).getChilds().size()).isEqualTo(2);
            assertThat(((Container)container.getChilds().get(0)).getChilds().get(0).getName()).isEqualTo("level_2");
            assertThat(((Container)container.getChilds().get(0)).getChilds().get(1).getName()).isEqualTo("page#2");
            assertThat(((Container)((Container)container.getChilds().get(0)).getChilds().get(0)).getChilds().get(0).getName()).isEqualTo("page#1");
        }catch(IOException e) {
            fail("Error reading test stream", e);
        }
    }

}
