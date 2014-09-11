package org.sample;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.*;

@State(Scope.Benchmark)
public class ListToStringBuilderBenchmark {

    private List<String> arrayList5 = new ArrayList<String>(5);
    private List<String> arrayList100 = new ArrayList<String>(100);
    private List<String> arrayList1000 = new ArrayList<String>(1000);

    private List<String> linkedList5 = new LinkedList<String>();
    private List<String> linkedList100 = new LinkedList<String>();
    private List<String> linkedList1000 = new LinkedList<String>();

    @Setup
    public void setUp() {
        for (int i = 0; i < 1000; i++) {
            String uuid = UUID.randomUUID().toString();
            if (i < 5) {
                arrayList5.add(uuid);
                linkedList5.add(uuid);
            }
            if (i < 100) {
                arrayList100.add(uuid);
                linkedList100.add(uuid);
            }
            if (i < 1000) {
                arrayList1000.add(uuid);
                linkedList1000.add(uuid);
            }
        }
    }

    @Benchmark
    public void testArrayList5IfFirst() {
        ListToStringBuilder.toStringIfFirst(arrayList5);
    }

    @Benchmark
    public void testArrayList5IfLast() {
        ListToStringBuilder.toStringIfLast(arrayList5);
    }

    @Benchmark
    public void testArrayList5SeparatorChar() {
        ListToStringBuilder.toStringSeparatorChar(arrayList5);
    }

    @Benchmark
    public void testArrayList5SeparatorCharForEach() {
        ListToStringBuilder.toStringSeparatorCharForEach(arrayList5);
    }

    @Benchmark
    public void testArrayList100IfFirst() {
        ListToStringBuilder.toStringIfFirst(arrayList100);
    }

    @Benchmark
    public void testArrayList100IfLast() {
        ListToStringBuilder.toStringIfLast(arrayList100);
    }

    @Benchmark
    public void testArrayList100SeparatorChar() {
        ListToStringBuilder.toStringSeparatorChar(arrayList100);
    }

    @Benchmark
    public void testArrayList100SeparatorCharForEach() {
        ListToStringBuilder.toStringSeparatorCharForEach(arrayList100);
    }

    @Benchmark
    public void testArrayList1000IfFirst() {
        ListToStringBuilder.toStringIfFirst(arrayList1000);
    }

    @Benchmark
    public void testArrayList1000IfLast() {
        ListToStringBuilder.toStringIfLast(arrayList1000);
    }

    @Benchmark
    public void testArrayList1000SeparatorChar() {
        ListToStringBuilder.toStringSeparatorChar(arrayList1000);
    }

    @Benchmark
    public void testArrayList1000SeparatorCharForEach() {
        ListToStringBuilder.toStringSeparatorCharForEach(arrayList1000);
    }
    @Benchmark
    public void testLinkedList5IfFirst() {
        ListToStringBuilder.toStringIfFirst(linkedList5);
    }

    @Benchmark
    public void testLinkedList5IfLast() {
        ListToStringBuilder.toStringIfLast(linkedList5);
    }

    @Benchmark
    public void testLinkedList5SeparatorChar() {
        ListToStringBuilder.toStringSeparatorChar(linkedList5);
    }

    @Benchmark
    public void testLinkedList5SeparatorCharForEach() {
        ListToStringBuilder.toStringSeparatorCharForEach(linkedList5);
    }

    @Benchmark
    public void testLinkedList100IfFirst() {
        ListToStringBuilder.toStringIfFirst(linkedList100);
    }

    @Benchmark
    public void testLinkedList100IfLast() {
        ListToStringBuilder.toStringIfLast(linkedList100);
    }

    @Benchmark
    public void testLinkedList100SeparatorChar() {
        ListToStringBuilder.toStringSeparatorChar(linkedList100);
    }

    @Benchmark
    public void testLinkedList100SeparatorCharForEach() {
        ListToStringBuilder.toStringSeparatorCharForEach(linkedList100);
    }

    @Benchmark
    public void testLinkedList1000IfFirst() {
        ListToStringBuilder.toStringIfFirst(linkedList1000);
    }

    @Benchmark
    public void testLinkedList1000IfLast() {
        ListToStringBuilder.toStringIfLast(linkedList1000);
    }

    @Benchmark
    public void testLinkedList1000SeparatorChar() {
        ListToStringBuilder.toStringSeparatorChar(linkedList1000);
    }

    @Benchmark
    public void testLinkedList1000SeparatorCharForEach() {
        ListToStringBuilder.toStringSeparatorCharForEach(linkedList1000);
    }

}
