/*
 * Copyright (c) Rakuten, Inc. All Rights Reserved.
 * 
 * This program is the information assets which are handled
 * as "Strictly Confidential".
 * Permission of Use is only admitted in Rakuten Inc.
 * Development Department.
 * If you don't have permission , MUST not be published,
 * broadcast, rewritten for broadcast or publication
 * or redistributed directly or indirectly in any medium.
 * 
 * $Id$
 * Created on Nov 2, 2015
 * Updated on $Date$
 */
package org.gradle;

import rx.Scheduler;

/**
 * Creates {@link Scheduler} bean from {@link RxThreadPoolExecutor}.
 * 
 * @author Venkatesha Chandru
 *
 */
public class RxSchedulerConfig {/*

    */
    /**
     * Make sure this class is initialized before calling any Schedulers method
     * 
     * TODO - Remove this static block if custom executor is used to create scheduler
     */
    /*
     * static {
     * // RxJavaPlugins.getInstance().registerSchedulersHook(new RequestContextSchedulersHook());
     * }
     *//** The Constant RX_JAVA_THREAD_POOL. */
    /*
     * private static final String RX_JAVA_THREAD_POOL = "RxJavaThreadPool-";
     *//**
     * The number of threads to keep in the pool, even if they are idle, unless allowCoreThreadTimeOut is set
     */
    /*
     * private int rxThreadCorePoolSize = 3;
     *//**
     * The maximum number of threads to allow in the pool
     */
    /*
     * private int rxThreadMaxPoolSize = 5;
     *//**
     * When the number of threads is greater than the core, this is the maximum time that excess idle threads will
     * wait
     * for new tasks before terminating.
     */
    /*
     * private int rxThreadKeepAliveTime = 30;
     * 
     * private Scheduler scheduler;
     *//**
     * Creates scheduler of singleton scope.
     *
     * @return the scheduler
     */
    /*
     * public Scheduler scheduler() {
     * if (scheduler == null) {
     * 
     * synchronized (this.scheduler) {
     * if (scheduler == null) {
     * 
     * Executor executor = new RxThreadPoolExecutor(rxThreadCorePoolSize, rxThreadMaxPoolSize,
     * rxThreadKeepAliveTime,
     * TimeUnit.SECONDS, new LinkedBlockingDeque<>(), new RxThreadFactory(RX_JAVA_THREAD_POOL));
     * scheduler = Schedulers.from(executor);
     * 
     * }
     * }
     * 
     * }
     * return scheduler;
     * }
     *//**
     * Customized thread pool executor for
     *
     * @author Venkatesha Chandru
     */
    /*
     * private class RxThreadPoolExecutor extends ThreadPoolExecutor {
     * 
     * public RxThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
     * BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
     * super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
     * }
     * 
     * @Override
     * public void execute(Runnable command) {
     * super.execute(new WrappedRunnable(command));
     * }
     *//**
     * Used to set and clear Thread Local variables in New Thread.
     *
     * @author Venkatesha Chandru
     */
    /*
     * private class WrappedRunnable implements Runnable {
     * 
     * private Runnable command;
     * 
     * public WrappedRunnable(Runnable command) {
     * this.command = command;
     * }
     * 
     * @Override
     * public void run() {
     * try {
     * this.command.run();
     * } finally {
     * }
     * }
     * }
     * }
     *//**
     * A factory for creating RxThread objects.
     */
    /*
     * private static class RxThreadFactory extends AtomicLong implements ThreadFactory {
     *//** The Constant serialVersionUID. */
    /*
     * private static final long serialVersionUID = 1L;
     * 
     * private String prefix;
     * 
     * public RxThreadFactory(String prefix) {
     * this.prefix = prefix;
     * }
     * 
     * @Override
     * public Thread newThread(Runnable target) {
     * Thread thread = new Thread(target);
     * thread.setName(this.prefix + incrementAndGet());
     * return thread;
     * }
     * 
     * }
     *//**
     * The Class RequestContextSchedulersHook.
     * 
     * TODO - Remove this class if custom executor is used to create scheduler
     *
     * @author Venkatesha Chandru
     */
    /*
     * protected static class RequestContextSchedulersHook extends RxJavaSchedulersHook {
     *//**
     * Invoked before the Action is handed over to the scheduler. Can be used for wrapping/decorating/logging.
     * The default is just a pass through.
     * 
     * @param action
     *            action to schedule
     * @return wrapped action to schedule
     */
    /*
     * @Override
     * public Action0 onSchedule(Action0 action) {
     * return super.onSchedule(new WrappedAction(action));
     * }
     * 
     * @Override
     * public Scheduler getIOScheduler() {
     * return Schedulers.io();
     * }
     *//**
     * Used to wrap thread local variables.
     *
     * @author Venkatesha Chandru
     */
    /*
     * private static class WrappedAction implements Action0 {
     * 
     * private Action0 action0;
     * 
     * public WrappedAction(Action0 action0) {
     * this.action0 = action0;
     * }
     * 
     * @Override
     * public void call() {
     * try {
     * this.action0.call();
     * } finally {
     * }
     * }
     * }
     * }
     */
}
