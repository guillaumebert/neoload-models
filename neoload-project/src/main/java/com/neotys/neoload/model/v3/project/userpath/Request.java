package com.neotys.neoload.model.v3.project.userpath;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ValidationMethod;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.neotys.neoload.model.v3.project.Element;
import com.neotys.neoload.model.v3.validation.constraints.RequiredCheck;
import com.neotys.neoload.model.v3.validation.groups.NeoLoad;

@JsonInclude(value=Include.NON_EMPTY)
@JsonPropertyOrder({Request.URL})
@JsonDeserialize(as = ImmutableRequest.class)
@Value.Immutable
@Value.Style(validationMethod = ValidationMethod.NONE)
public interface Request extends Element {
	public static final String URL = "url";

	@JsonProperty(URL)
	@RequiredCheck(groups={NeoLoad.class})
	String getUrl();
}