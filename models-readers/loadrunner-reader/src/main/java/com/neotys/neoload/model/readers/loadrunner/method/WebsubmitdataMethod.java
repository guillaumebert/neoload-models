package com.neotys.neoload.model.readers.loadrunner.method;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.neotys.neoload.model.core.Element;
import com.neotys.neoload.model.parsers.CPP14Parser.MethodcallContext;
import com.neotys.neoload.model.readers.loadrunner.LoadRunnerVUVisitor;
import com.neotys.neoload.model.readers.loadrunner.MethodCall;
import com.neotys.neoload.model.readers.loadrunner.WebSubmitData;
import com.neotys.neoload.model.repository.Page;

public class WebsubmitdataMethod  implements LoadRunnerMethod {
	
	public WebsubmitdataMethod() {
		super();
	}

	@Override
	public List<Element> getElement(LoadRunnerVUVisitor visitor, MethodCall method, MethodcallContext ctx) {
		visitor.readSupportedFunction(method.getName(), ctx);
		final Page page = WebSubmitData.toElement(visitor, method);
		visitor.getCurrentExtractors().clear();
		visitor.getCurrentValidators().clear();
		visitor.getCurrentHeaders().clear();
		visitor.setCurrentRequestFromPage(page);
		return ImmutableList.of(page);
	}

}
