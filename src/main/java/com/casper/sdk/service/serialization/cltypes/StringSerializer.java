package com.casper.sdk.service.serialization.cltypes;

import com.casper.sdk.domain.CLType;
import com.casper.sdk.service.serialization.util.ByteUtils;

import java.nio.charset.StandardCharsets;

class StringSerializer implements TypesSerializer {

    private final TypesFactory typesFactory;

    public StringSerializer(final TypesFactory typesFactory) {
        this.typesFactory = typesFactory;
    }

    @Override
    public byte[] serialize(final Object toSerialize) {

        final String str = toSerialize != null ? toSerialize.toString() : "";
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);

        //return the length of the string in U32 type plus the hex byte value of the string
        return ByteUtils.concat(
                typesFactory.getInstance(CLType.U32).serialize(bytes.length),
                bytes
        );
    }
}