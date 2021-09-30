package com.casper.sdk.types;

import java.util.List;
import java.util.Optional;

public class StoredVersionedContractByHash extends StoredContractByHash {

    /**
     * version: Option<ContractVersion> defaults to highest enabled version can be version: None in JSON
     */
    private final Optional<Long> version;

    public StoredVersionedContractByHash(final ContractHash hash,
                                         final String entryPoint,
                                         final Long version,
                                         final List<DeployNamedArg> args) {
        super(hash, entryPoint, args);
        this.version = Optional.ofNullable(version);
    }

    public Optional<Long> getVersion() {
        return version;
    }

    @Override
    public int getTag() {
        return 3;
    }
}
