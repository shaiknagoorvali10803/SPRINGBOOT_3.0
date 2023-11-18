package com.spring.springselenium.DatabaseUtils;

import com.spring.springselenium.Configuraion.annotation.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
@Page
public class DataBaseUtils {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> FetchDatabaseRecords(String query) {
        List<Map<String, Object>> results = jdbcTemplate.queryForList(query);
        return results;
    }

    public String[][] FetchDatabaseRecordsArray(String query) throws SQLException {
        ResultSetMetaData rsmd = jdbcTemplate.query(query, ResultSet::getMetaData);
        String[][] resultData = new String[5][3];
        jdbcTemplate.query(query, new ResultSetExtractor<Object>() {
            @Override
            public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
                String test ;
                int rowCnt, colCnt;
                rowCnt = rs.getRow();
                colCnt = rsmd.getColumnCount();
                int i = 0;
                while (rs.next()) {
                    for (int j = 0; j < colCnt; j++) {
                        if(rs.getString(j + 1)!=null) {
                          resultData[i][j] = rs.getString(j + 1);
                        }
                    }
                    i++;
                }
                return null;
            }
        });
        return resultData;
    }
}
