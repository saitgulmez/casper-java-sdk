package com.casper.sdk.service.json.deserialize;

import com.casper.sdk.service.serialization.util.ByteUtils;
import com.casper.sdk.types.CLKeyInfo;
import com.casper.sdk.types.CLKeyValue;
import com.casper.sdk.types.CLType;
import com.casper.sdk.types.CLValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Unit tests for the {@link CLValueJsonDeserializer}
 */
class CLValueJsonDeserializerTest {

    private static final String U512_JSON =
            "{\n" +
            "   \"cl_type\": \"U512\",\n" +
            "   \"bytes\": \"05005550b405\",\n" +
            "   \"parsed\": \"24500000000\"\n" +
            "}";

    private static final String U64_JSON =
            "{\n" +
            "  \"cl_type\": \"U64\",\n" +
            "  \"bytes\": \"01e703000000000000\",\n" +
            "  \"parsed\": 999\n" +
            "}";

    private static final String BYTE_ARRAY_32_JSON =
            "{\n" +
            "  \"cl_type\": {\n" +
            "    \"ByteArray\": 32\n" +
            "  },\n" +
            "  \"bytes\": \"0101010101010101010101010101010101010101010101010101010101010101\",\n" +
            "  \"parsed\": \"0101010101010101010101010101010101010101010101010101010101010101\"\n" +
            "}";

    private static final String KEY_JSON =
            "{\n" +
            "   \"cl_type\": \"Key\",\n" +
            "   \"bytes\": \"012b177f0739348d33ce868b2f95bb83decf5b5dcc71279d4bec64c87f60b805d5\",\n" +
            "   \"parsed\": {\n" +
            "       \"Hash\": \"hash-2b177f0739348d33ce868b2f95bb83decf5b5dcc71279d4bec64c87f60b805d5\"\n" +
            "   }" +
            "}";

    @Test
    void deserializeU512CLValue() throws IOException {

        final CLValue clValue = new ObjectMapper().reader().readValue(U512_JSON, CLValue.class);
        assertThat(clValue, is(notNullValue()));
        assertThat(clValue.getCLTypeInfo().getType(), is(CLType.U512));
        assertThat(clValue.getBytes(), is(CLValue.fromString("05005550b405")));
        assertThat(clValue.getParsed(), is("24500000000"));
    }

    @Test
    void deserializeByteArray32CLValue() throws IOException {
        final CLValue clValue = new ObjectMapper().reader().readValue(BYTE_ARRAY_32_JSON, CLValue.class);
        assertThat(clValue, is(notNullValue()));
        assertThat(clValue.getCLTypeInfo().getType(), is(CLType.BYTE_ARRAY));
        assertThat(clValue.getBytes(), is(CLValue.fromString("0101010101010101010101010101010101010101010101010101010101010101")));
        assertThat(clValue.getParsed(), is("0101010101010101010101010101010101010101010101010101010101010101"));
    }

    @Test
    void deserializeU64CLValue() throws IOException {
        final CLValue clValue = new ObjectMapper().reader().readValue(U64_JSON, CLValue.class);
        assertThat(clValue, is(notNullValue()));
        assertThat(clValue.getCLTypeInfo().getType(), is(CLType.U64));
        assertThat(clValue.getBytes(), is(CLValue.fromString("01e703000000000000")));
        assertThat(clValue.getParsed(), is(BigInteger.valueOf(999L)));
    }

    @Test
    void deserializeCLKeyValue() throws IOException {

        final CLKeyValue clKeyValue = new ObjectMapper().reader().readValue(KEY_JSON, CLKeyValue.class);
        assertThat(clKeyValue.getCLTypeInfo().getType(), is(CLType.KEY));
        assertThat(clKeyValue.getKeyType(), is(CLKeyInfo.KeyType.HASH_ID));

        final byte[] expected = ByteUtils.decodeHex("012b177f0739348d33ce868b2f95bb83decf5b5dcc71279d4bec64c87f60b805d5");
        assertThat(clKeyValue.getBytes(), is(expected));
        assertThat(clKeyValue.getParsed(), is("hash-2b177f0739348d33ce868b2f95bb83decf5b5dcc71279d4bec64c87f60b805d5"));
    }
}