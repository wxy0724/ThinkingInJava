package com.lovetogether.myxml;

/**
 * SAX的全称是Simple APIs for XML，也即XML简单应用程序接口。
 * 与DOM不同，SAX提供的访问模式是一种顺序模式，这是一种快速读写XML数据的方式。
 * 当使用SAX分析器对XML文档进行分析时，会触发一系列事件，并激活相应的事件处理函数，
 * 应用程序通过这些事件处理函数实现对XML文档的访问，因而SAX接口也被称作事件驱动接口。
 　　　　优点：
 　　　　　　1、采用事件驱动模式，对内存耗费比较小。
 　　　　　　2、适用于只处理XML文件中的数据时。
 　　　　缺点：
 　　　　　　1、编码比较麻烦。
 　　　　　　2、很难同时访问XML文件中的多处不同数据。
 */
public class MySAX {
}