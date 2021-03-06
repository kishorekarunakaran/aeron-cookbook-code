package com.aeroncookbook.cluster.rfq.gen;

import org.agrona.DirectBuffer;
import org.agrona.MutableDirectBuffer;
import org.agrona.concurrent.UnsafeBuffer;

public class RfqCreatedEvent {
  /**
   * The eider spec id for this type. Useful in switch statements to detect type in first 16bits.
   */
  public static final short EIDER_ID = 5002;

  /**
   * The eider group id for this type. Useful in switch statements to detect group in second 16bits.
   */
  public static final short EIDER_GROUP_ID = 4;

  /**
   * The offset for the EIDER_ID within the buffer.
   */
  private static final int HEADER_OFFSET = 0;

  /**
   * The offset for the EIDER_GROUP_IP within the buffer.
   */
  private static final int HEADER_GROUP_OFFSET = 2;

  /**
   * The length offset. Required for segmented buffers.
   */
  private static final int LENGTH_OFFSET = 4;

  /**
   * The byte offset in the byte array for this INT. Byte length is 4.
   */
  private static final int CORRELATION_OFFSET = 8;

  /**
   * The byte offset in the byte array for this INT. Byte length is 4.
   */
  private static final int SECURITYID_OFFSET = 12;

  /**
   * The byte offset in the byte array for this LONG. Byte length is 8.
   */
  private static final int EXPIRETIMEMS_OFFSET = 16;

  /**
   * The byte offset in the byte array for this LONG. Byte length is 8.
   */
  private static final int QUANTITY_OFFSET = 24;

  /**
   * The byte offset in the byte array for this LONG. Byte length is 8.
   */
  private static final int LIMITPRICE_OFFSET = 32;

  /**
   * The byte offset in the byte array for this SHORT. Byte length is 2.
   */
  private static final int SIDE_OFFSET = 40;

  /**
   * The byte offset in the byte array for this INT. Byte length is 4.
   */
  private static final int RFQID_OFFSET = 42;

  /**
   * The byte offset in the byte array for this INT. Byte length is 4.
   */
  private static final int RFQREQUESTERUSERID_OFFSET = 46;

  /**
   * The byte offset in the byte array for this INT. Byte length is 4.
   */
  private static final int RFQREQUESTERCORRELATIONID_OFFSET = 50;

  /**
   * The total bytes required to store the object.
   */
  public static final int BUFFER_LENGTH = 54;

  /**
   * Indicates if this flyweight holds a fixed length object.
   */
  public static final boolean FIXED_LENGTH = true;

  /**
   * The internal DirectBuffer.
   */
  private DirectBuffer buffer = null;

  /**
   * The internal DirectBuffer used for mutatation opertions. Valid only if a mutable buffer was provided.
   */
  private MutableDirectBuffer mutableBuffer = null;

  /**
   * The internal UnsafeBuffer. Valid only if an unsafe buffer was provided.
   */
  private UnsafeBuffer unsafeBuffer = null;

  /**
   * The starting offset for reading and writing.
   */
  private int initialOffset;

  /**
   * Flag indicating if the buffer is mutable.
   */
  private boolean isMutable = false;

  /**
   * Flag indicating if the buffer is an UnsafeBuffer.
   */
  private boolean isUnsafe = false;

  /**
   * Uses the provided {@link org.agrona.DirectBuffer} from the given offset.
   * @param buffer - buffer to read from and write to.
   * @param offset - offset to begin reading from/writing to in the buffer.
   */
  public void setUnderlyingBuffer(DirectBuffer buffer, int offset) {
    this.initialOffset = offset;
    this.buffer = buffer;
    if (buffer instanceof UnsafeBuffer) {
      unsafeBuffer = (UnsafeBuffer) buffer;
      mutableBuffer = (MutableDirectBuffer) buffer;
      isUnsafe = true;
      isMutable = true;
    }
    else if (buffer instanceof MutableDirectBuffer) {
      mutableBuffer = (MutableDirectBuffer) buffer;
      isUnsafe = false;
      isMutable = true;
    }
    else {
      isUnsafe = false;
      isMutable = false;
    }
    buffer.checkLimit(initialOffset + BUFFER_LENGTH);
  }

  /**
   * Uses the provided {@link org.agrona.DirectBuffer} from the given offset.
   * @param buffer - buffer to read from and write to.
   * @param offset - offset to begin reading from/writing to in the buffer.
   */
  public void setBufferWriteHeader(DirectBuffer buffer, int offset) {
    setUnderlyingBuffer(buffer, offset);
    writeHeader();
  }

  /**
   * Returns the eider sequence.
   * @return EIDER_ID.
   */
  public short eiderId() {
    return EIDER_ID;
  }

  /**
   * Writes the header data to the buffer.
   */
  public void writeHeader() {
    if (!isMutable) throw new RuntimeException("cannot write to immutable buffer");
    mutableBuffer.putShort(initialOffset + HEADER_OFFSET, EIDER_ID, java.nio.ByteOrder.LITTLE_ENDIAN);
    mutableBuffer.putShort(initialOffset + HEADER_GROUP_OFFSET, EIDER_GROUP_ID, java.nio.ByteOrder.LITTLE_ENDIAN);
    mutableBuffer.putInt(initialOffset + LENGTH_OFFSET, BUFFER_LENGTH, java.nio.ByteOrder.LITTLE_ENDIAN);
  }

  /**
   * Validates the length and eiderSpecId in the header against the expected values. False if invalid.
   */
  public boolean validateHeader() {
    final short eiderId = buffer.getShort(initialOffset + HEADER_OFFSET, java.nio.ByteOrder.LITTLE_ENDIAN);
    final short eiderGroupId = buffer.getShort(initialOffset + HEADER_GROUP_OFFSET, java.nio.ByteOrder.LITTLE_ENDIAN);
    final int bufferLength = buffer.getInt(initialOffset + LENGTH_OFFSET, java.nio.ByteOrder.LITTLE_ENDIAN);
    if (eiderId != EIDER_ID) return false;
    if (eiderGroupId != EIDER_GROUP_ID) return false;
    return bufferLength == BUFFER_LENGTH;
  }

  /**
   * Reads correlation as stored in the buffer.
   */
  public int readCorrelation() {
    return buffer.getInt(initialOffset + CORRELATION_OFFSET, java.nio.ByteOrder.LITTLE_ENDIAN);
  }

  /**
   * Writes correlation to the buffer. Returns true if success, false if not.
   * @param value Value for the correlation to write to buffer.
   */
  public boolean writeCorrelation(int value) {
    if (!isMutable) throw new RuntimeException("Cannot write to immutable buffer");
    mutableBuffer.putInt(initialOffset + CORRELATION_OFFSET, value, java.nio.ByteOrder.LITTLE_ENDIAN);
    return true;
  }

  /**
   * Reads securityId as stored in the buffer.
   */
  public int readSecurityId() {
    return buffer.getInt(initialOffset + SECURITYID_OFFSET, java.nio.ByteOrder.LITTLE_ENDIAN);
  }

  /**
   * Writes securityId to the buffer. Returns true if success, false if not.
   * @param value Value for the securityId to write to buffer.
   */
  public boolean writeSecurityId(int value) {
    if (!isMutable) throw new RuntimeException("Cannot write to immutable buffer");
    mutableBuffer.putInt(initialOffset + SECURITYID_OFFSET, value, java.nio.ByteOrder.LITTLE_ENDIAN);
    return true;
  }

  /**
   * Reads expireTimeMs as stored in the buffer.
   */
  public long readExpireTimeMs() {
    return buffer.getLong(initialOffset + EXPIRETIMEMS_OFFSET, java.nio.ByteOrder.LITTLE_ENDIAN);
  }

  /**
   * Writes expireTimeMs to the buffer. Returns true if success, false if not.
   * @param value Value for the expireTimeMs to write to buffer.
   */
  public boolean writeExpireTimeMs(long value) {
    if (!isMutable) throw new RuntimeException("Cannot write to immutable buffer");
    mutableBuffer.putLong(initialOffset + EXPIRETIMEMS_OFFSET, value, java.nio.ByteOrder.LITTLE_ENDIAN);
    return true;
  }

  /**
   * Reads quantity as stored in the buffer.
   */
  public long readQuantity() {
    return buffer.getLong(initialOffset + QUANTITY_OFFSET, java.nio.ByteOrder.LITTLE_ENDIAN);
  }

  /**
   * Writes quantity to the buffer. Returns true if success, false if not.
   * @param value Value for the quantity to write to buffer.
   */
  public boolean writeQuantity(long value) {
    if (!isMutable) throw new RuntimeException("Cannot write to immutable buffer");
    mutableBuffer.putLong(initialOffset + QUANTITY_OFFSET, value, java.nio.ByteOrder.LITTLE_ENDIAN);
    return true;
  }

  /**
   * Reads limitPrice as stored in the buffer.
   */
  public long readLimitPrice() {
    return buffer.getLong(initialOffset + LIMITPRICE_OFFSET, java.nio.ByteOrder.LITTLE_ENDIAN);
  }

  /**
   * Writes limitPrice to the buffer. Returns true if success, false if not.
   * @param value Value for the limitPrice to write to buffer.
   */
  public boolean writeLimitPrice(long value) {
    if (!isMutable) throw new RuntimeException("Cannot write to immutable buffer");
    mutableBuffer.putLong(initialOffset + LIMITPRICE_OFFSET, value, java.nio.ByteOrder.LITTLE_ENDIAN);
    return true;
  }

  /**
   * Reads side as stored in the buffer.
   */
  public short readSide() {
    return buffer.getShort(initialOffset + SIDE_OFFSET);
  }

  /**
   * Writes side to the buffer. Returns true if success, false if not.
   * @param value Value for the side to write to buffer.
   */
  public boolean writeSide(short value) {
    if (!isMutable) throw new RuntimeException("Cannot write to immutable buffer");
    mutableBuffer.putShort(initialOffset + SIDE_OFFSET, value, java.nio.ByteOrder.LITTLE_ENDIAN);
    return true;
  }

  /**
   * Reads rfqId as stored in the buffer.
   */
  public int readRfqId() {
    return buffer.getInt(initialOffset + RFQID_OFFSET, java.nio.ByteOrder.LITTLE_ENDIAN);
  }

  /**
   * Writes rfqId to the buffer. Returns true if success, false if not.
   * @param value Value for the rfqId to write to buffer.
   */
  public boolean writeRfqId(int value) {
    if (!isMutable) throw new RuntimeException("Cannot write to immutable buffer");
    mutableBuffer.putInt(initialOffset + RFQID_OFFSET, value, java.nio.ByteOrder.LITTLE_ENDIAN);
    return true;
  }

  /**
   * Reads rfqRequesterUserId as stored in the buffer.
   */
  public int readRfqRequesterUserId() {
    return buffer.getInt(initialOffset + RFQREQUESTERUSERID_OFFSET, java.nio.ByteOrder.LITTLE_ENDIAN);
  }

  /**
   * Writes rfqRequesterUserId to the buffer. Returns true if success, false if not.
   * @param value Value for the rfqRequesterUserId to write to buffer.
   */
  public boolean writeRfqRequesterUserId(int value) {
    if (!isMutable) throw new RuntimeException("Cannot write to immutable buffer");
    mutableBuffer.putInt(initialOffset + RFQREQUESTERUSERID_OFFSET, value, java.nio.ByteOrder.LITTLE_ENDIAN);
    return true;
  }

  /**
   * Reads rfqRequesterCorrelationId as stored in the buffer.
   */
  public int readRfqRequesterCorrelationId() {
    return buffer.getInt(initialOffset + RFQREQUESTERCORRELATIONID_OFFSET, java.nio.ByteOrder.LITTLE_ENDIAN);
  }

  /**
   * Writes rfqRequesterCorrelationId to the buffer. Returns true if success, false if not.
   * @param value Value for the rfqRequesterCorrelationId to write to buffer.
   */
  public boolean writeRfqRequesterCorrelationId(int value) {
    if (!isMutable) throw new RuntimeException("Cannot write to immutable buffer");
    mutableBuffer.putInt(initialOffset + RFQREQUESTERCORRELATIONID_OFFSET, value, java.nio.ByteOrder.LITTLE_ENDIAN);
    return true;
  }

  /**
   * True if transactions are supported; false if not.
   */
  public boolean supportsTransactions() {
    return false;
  }
}
