package com.casper.sdk.service.json.deserialize;

import com.casper.sdk.service.serialization.util.ByteUtils;
import com.casper.sdk.types.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

class DeployExecutableJsonDeserializerTest {

    private static final String STORED_CONTRACT_BY_HASH_JSON =
            "{\n" +
            "    \"StoredContractByHash\": {\n" +
            "      \"hash\": \"0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f\",\n" +
            "      \"entry_point\": \"pclphXwfYmCmdITj8hnh\",\n" +
            "      \"args\": [\n" +
            "        [\n" +
            "            \"amount\",\n" +
            "          {\n" +
            "            \"cl_type\": \"U512\",\n" +
            "            \"bytes\": \"0400ca9a3b\",\n" +
            "            \"parsed\": \"1000000000\"\n" +
            "          }\n" +
            "        ],\n" +
            "        [\n" +
            "          \"instrument_state_hash\",\n" +
            "          {\n" +
            "            \"cl_type\": \"Key\",\n" +
            "            \"bytes\": \"012b177f0739348d33ce868b2f95bb83decf5b5dcc71279d4bec64c87f60b805d5\",\n" +
            "            \"parsed\": {\n" +
            "              \"Hash\": \"hash-2b177f0739348d33ce868b2f95bb83decf5b5dcc71279d4bec64c87f60b805d5\"\n" +
            "            }\n" +
            "          }\n" +
            "        ]" +
            "      ]\n" +
            "    }\n" +
            "}";


    private static final String STORED_CONTRACT_BY_NAME_JSON =
            "{\n" +
            "    \"StoredContractByName\": {\n" +
            "      \"name\": \"U5A74bSZH8abT8HqVaK9\",\n" +
            "      \"entry_point\": \"gIetSxltnRDvMhWdxTqQ\",\n" +
            "      \"args\": [\n" +
            "        [\n" +
            "            \"amount\",\n" +
            "          {\n" +
            "            \"cl_type\": \"U512\",\n" +
            "            \"bytes\": \"0400ca9a3b\",\n" +
            "            \"parsed\": \"1000000000\"\n" +
            "          }\n" +
            "        ]\n" +
            "      ]\n" +
            "    }\n" +
            "}";


    private static final String STORED_VERSIONED_CONTRACT_BY_HASH_JSON =
            "{\n" +
            "    \"StoredVersionedContractByHash\": {\n" +
            "      \"hash\": \"0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f\",\n" +
            "      \"entry_point\": \"pclphXwfYmCmdITj8hnh\",\n" +
            "      \"version\": Some(1632552656),\n" +
            "      \"args\": [\n" +
            "        [\n" +
            "            \"amount\",\n" +
            "          {\n" +
            "            \"cl_type\": \"U512\",\n" +
            "            \"bytes\": \"0400ca9a3b\",\n" +
            "            \"parsed\": \"1000000000\"\n" +
            "          }\n" +
            "        ],\n" +
            "        [\n" +
            "          \"instrument_state_hash\",\n" +
            "          {\n" +
            "            \"cl_type\": \"Key\",\n" +
            "            \"bytes\": \"012b177f0739348d33ce868b2f95bb83decf5b5dcc71279d4bec64c87f60b805d5\",\n" +
            "            \"parsed\": {\n" +
            "              \"Hash\": \"hash-2b177f0739348d33ce868b2f95bb83decf5b5dcc71279d4bec64c87f60b805d5\"\n" +
            "            }\n" +
            "          }\n" +
            "        ]" +
            "      ]\n" +
            "    }\n" +
            "}";

    private static final String STORED_VERSIONED_CONTRACT_BY_NAME_JSON = "";

    @Test
    void parseStoredContractByHashFromJson() throws IOException {

        final ObjectMapper mapper = new ObjectMapper();
        final DeployExecutable deployExecutable = mapper.reader().readValue(STORED_CONTRACT_BY_HASH_JSON, DeployExecutable.class);
        assertThat(deployExecutable, is(instanceOf(StoredContractByHash.class)));

        final StoredContractByHash storedContractByHash = (StoredContractByHash) deployExecutable;
        assertThat(storedContractByHash.getHash().toString(), is("0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f"));
        assertThat(storedContractByHash.getEntryPoint(), is("pclphXwfYmCmdITj8hnh"));
        assertThat(storedContractByHash.getArgs().size(), is(2));
        final DeployNamedArg instrumentStateHash = storedContractByHash.getArgs().get(1);
        assertThat(instrumentStateHash.getName(), is("instrument_state_hash"));
        assertThat(instrumentStateHash.getValue().getCLType(), is(CLType.KEY));
        assertThat(instrumentStateHash.getValue().getBytes(), is(ByteUtils.decodeHex("012b177f0739348d33ce868b2f95bb83decf5b5dcc71279d4bec64c87f60b805d5")));
        assertThat(instrumentStateHash.getValue().getParsed(), is("hash-2b177f0739348d33ce868b2f95bb83decf5b5dcc71279d4bec64c87f60b805d5"));
    }

    @Test
    void parseStoredContractByNameFromJson() throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final DeployExecutable deployExecutable = mapper.reader().readValue(STORED_CONTRACT_BY_NAME_JSON, DeployExecutable.class);
        assertThat(deployExecutable, is(instanceOf(StoredContractByName.class)));

        final StoredContractByName storedContractByName = (StoredContractByName) deployExecutable;
        assertThat(storedContractByName.getName(), is("U5A74bSZH8abT8HqVaK9"));
        assertThat(storedContractByName.getEntryPoint(), is("gIetSxltnRDvMhWdxTqQ"));
        assertThat(storedContractByName.getArgs().size(), is(1));
    }


    @Test
    void parseStoredVersionedContractByName() throws IOException {

        final ObjectMapper mapper = new ObjectMapper();
        final DeployExecutable deployExecutable = mapper.reader().readValue(STORED_VERSIONED_CONTRACT_BY_NAME_JSON, DeployExecutable.class);
        assertThat(deployExecutable, is(instanceOf(StoredVersionedContractByName.class)));

        final StoredVersionedContractByName storedVersionedContractByName = (StoredVersionedContractByName) deployExecutable;
        assertThat(storedVersionedContractByName.getVersion().isPresent(), is(true));


    }

    @Test
    void parseStoredVersionedContractByHash() throws IOException {

        final ObjectMapper mapper = new ObjectMapper();
        final DeployExecutable deployExecutable = mapper.reader().readValue(STORED_VERSIONED_CONTRACT_BY_HASH_JSON, DeployExecutable.class);
        assertThat(deployExecutable, is(instanceOf(StoredVersionedContractByName.class)));

        final StoredVersionedContractByHash storedVersionedContractByName = (StoredVersionedContractByHash) deployExecutable;
        assertThat(storedVersionedContractByName.getVersion().isPresent(), is(true));
    }
}