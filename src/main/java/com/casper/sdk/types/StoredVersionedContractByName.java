package com.casper.sdk.types;

import java.util.List;
import java.util.Optional;

public class StoredVersionedContractByName extends StoredContractByName {

    /**
     * version:     Option<ContractVersion> defaults to highest enabled version can be version: None in JSON
     */
    private final Optional<Long> version;

    public StoredVersionedContractByName(final String name,
                                         final String entryPoint,
                                         final Long version,
                                         final byte[] moduleBytes,
                                         final List<DeployNamedArg> args) {

        super(name, entryPoint, moduleBytes, args);
        this.version = Optional.ofNullable(version);
    }

    public Optional<Long> getVersion() {
        return version;
    }

    @Override
    public int getTag() {
        return 4;
    }
}
