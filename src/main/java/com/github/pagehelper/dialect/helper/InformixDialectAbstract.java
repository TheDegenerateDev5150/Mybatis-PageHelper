package com.github.pagehelper.dialect.helper;

import com.github.pagehelper.Page;
import com.github.pagehelper.dialect.AbstractHelperDialect;
import org.apache.ibatis.cache.CacheKey;

/**
 * @author liuzh
 */
public class InformixDialectAbstract extends AbstractHelperDialect {

    @Override
    public String getPageSql(String sql, Page page, CacheKey pageKey) {
        StringBuilder sqlBuilder = new StringBuilder(sql.length() + 40);
        sqlBuilder.append("SELECT ");
        if (page.getStartRow() > 0) {
            sqlBuilder.append(" SKIP ");
            sqlBuilder.append(page.getStartRow());
            pageKey.update(page.getStartRow());
        }
        if (page.getPageSize() > 0) {
            sqlBuilder.append(" FIRST ");
            sqlBuilder.append(page.getPageSize());
            pageKey.update(page.getPageSize());
        }
        sqlBuilder.append(" * FROM ( ");
        sqlBuilder.append(sql);
        sqlBuilder.append(" ) TEMP_T");
        return sqlBuilder.toString();
    }

}