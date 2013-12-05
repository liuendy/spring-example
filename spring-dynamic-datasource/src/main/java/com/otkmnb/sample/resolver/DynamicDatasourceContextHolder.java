package com.otkmnb.sample.resolver;

/**
 * <p>
 * このクラスは、データソースのタイプをスレッドローカルで管理します。
 * </p>
 * @author OHTAKE Manabu(manabu2783 at hotmail.com)
 */
public final class DynamicDatasourceContextHolder {

    private static final ThreadLocal<DatasourceType> CONTEXT = new ThreadLocal<>();
    
    /**
     * コンストラクタです。
     * インスタンスを抑止します。
     */
    private DynamicDatasourceContextHolder() {}
    
    /**
     * スレッドローカルにデータソースのタイプを格納します。
     * @param type データソースのタイプ
     */
    public static void set(DatasourceType type) {
        CONTEXT.set(type);
    }
    
    /**
     * スレッドローカルからデータソースのタイプを取得します。
     * @return データソースのタイプ
     */
    public static DatasourceType get() {
        return CONTEXT.get();
    }
    
    /**
     * スレッドローカルを解放します。
     */
    public static void remove() {
        CONTEXT.remove();
    }
}
