package io.netty.example.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

public class IterableTest {

	public static void main(String[] args) {
		ByteBuf header = Unpooled.wrappedBuffer(new byte[]{1, 2, 3});
		ByteBuf body = Unpooled.wrappedBuffer(new byte[]{4, 5, 6});
		ByteBuf merge = merge(header, body);
		merge.forEachByte(value -> {
			System.out.println(value);
			return true;
		});
	}

	private static ByteBuf merge(ByteBuf header, ByteBuf body) {
		// 非零拷贝方式
		//		ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
//		byteBuf.writeBytes(header);
//		byteBuf.writeBytes(body);
		// 零拷贝方式
		CompositeByteBuf byteBuf = ByteBufAllocator.DEFAULT.compositeBuffer();
		byteBuf.addComponent(true, header);
		byteBuf.addComponent(true, body);
		return byteBuf;
	}
}
