package com.neotys.neoload.model.v3.project.userpath;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ValidationMethod;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = ImmutableTransaction.class)
@Value.Immutable
@Value.Style(validationMethod = ValidationMethod.NONE)
public interface Transaction extends Container {
}