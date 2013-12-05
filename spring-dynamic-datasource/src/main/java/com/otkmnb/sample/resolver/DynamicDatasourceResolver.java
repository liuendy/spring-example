package com.otkmnb.sample.resolver;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * <p>
 * このクラスは、データソースのタイプに応じて利用するデータソースをルーティングします。
 * </p>
 * @author OHTAKE Manabu(manabu2783 at hotmail.com)
 */
public class DynamicDatasourceResolver extends AbstractRoutingDataSource {

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDatasourceContextHolder.get();
    }

}
