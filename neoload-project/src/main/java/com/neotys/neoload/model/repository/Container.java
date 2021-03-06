package com.neotys.neoload.model.repository;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableContainer.class)
public interface Container extends IContainer {
}
