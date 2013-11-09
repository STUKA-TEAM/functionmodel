package tools;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title: DataBaseUtil
 * @Description: Connect to database and execute the sql
 * @Company: ZhongHe
 * @author dai
 * @date 2013年11月5日
 */
public class DataBaseUtil {
    private Connection mConn = null;
    private String mUrl = null;
    private String mUser = null;
    private String mPwd = null;
    private boolean mIsAlive = true;
    private static DbList mDbList = null;
    private static int mInstanceCnt = 10;

    private DataBaseUtil(String url, String user, String pwd) {
        // 加载驱动，这一句也可写为：Class.forName("com.mysql.jdbc.Driver");
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            mUrl = url;
            mUser = user;
            mPwd = pwd;
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @Title: init
     * @Description: Initialization and return the instance of the database
     * @param url
     *            url to connect the database
     * @param user
     *            username of the database
     * @param pwd
     *            password of the database
     * @date 2013年11月6日
     */
    public static DataBaseUtil init(String url, String user, String pwd) {
        if (mDbList == null) {
            mDbList = new DbList(mInstanceCnt);
        }
        if (mDbList.isFull()) {
            return mDbList.getInstance();
        } else {
            DataBaseUtil dbUtil = new DataBaseUtil(url, user, pwd);
            mDbList.push(dbUtil);
            return dbUtil;
        }
    }

    /**
     * @Title: init
     * @Description: Initialization and return the instance of the database
     * @param url
     *            url to connect the database
     * @date 2013年11月6日
     */
    public static DataBaseUtil init(String url) {
        return init(url, Constant.gDbUserName, Constant.gDbPwd);
    }

    /**
     * @Title: isAlive
     * @Description: check if the instance is using
     * @date 2013年11月6日
     */
    public boolean isAlive() {
        return mIsAlive;
    }

    /**
     * @Title: SqlQuery
     * @Description: Execute the query sql
     * @param sql
     * @return ResultSet
     * @date 2013年11月6日
     */
    public ResultSet SqlQuery(String sql) {
        if(mIsAlive) return null;
        mIsAlive = false;
        ResultSet rs = null;
        try {
            // 建立到MySQL的连接
            mConn = DriverManager.getConnection(mUrl, mUser, mPwd);
            Statement statement = mConn.createStatement();
            rs = statement.executeQuery(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            mConn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mIsAlive = true;
        return rs;
    }

    /**
     * @Title: SqlQuery
     * @Description: Execute the query sqls array
     * @param sqls
     *            sql array
     * @return ResultSet
     * @date 2013年11月6日
     */
    public List<ResultSet> SqlQuery(String[] sqls) {
        if(mIsAlive) return null;
        mIsAlive = false;
        List<ResultSet> rs = new ArrayList<ResultSet>();
        try {
            // 建立到MySQL的连接
            mConn = DriverManager.getConnection(mUrl, mUser, mPwd);
            Statement statement = mConn.createStatement();
            for (String sql : sqls)
                rs.add(statement.executeQuery(sql));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            mConn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mIsAlive = true;
        return rs;
    }

    /**
     * @Title: SqlQuery
     * @Description: Execute the insert/update/delete sql
     * @param sql
     * @return int
     * @date 2013年11月6日
     */
    public int SqlExec(String sql) {
        if(mIsAlive) return -1;
        mIsAlive = false;
        int res = -1;
        try {
            // 建立到MySQL的连接
            mConn = DriverManager.getConnection(mUrl, mUser, mPwd);
            Statement statement = mConn.createStatement();
            res = statement.executeUpdate(sql);
            mConn.commit();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            mConn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mIsAlive = true;
        return res;
    }

    /**
     * @Title: SqlQuery
     * @Description: Execute the insert/update/delete sql array
     * @param sqls
     *            sql array
     * @return int
     * @date 2013年11月6日
     */
    public List<Integer> SqlExec(String[] sqls) {
        if(mIsAlive) return null;
        mIsAlive = false;
        List<Integer> res = new ArrayList<Integer>();
        try {
            // 建立到MySQL的连接
            mConn = DriverManager.getConnection(mUrl, mUser, mPwd);
            Statement statement = mConn.createStatement();
            for (String sql : sqls)
                res.add(statement.executeUpdate(sql));
            mConn.commit();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            mConn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mIsAlive = true;
        return res;
    }
}

class DbList {
    private List<DataBaseUtil> mList;
    private int mMaxVolume;

    public DbList(int volume) {
        mMaxVolume = volume;
        mList = new ArrayList<DataBaseUtil>();
    }

    public void push(DataBaseUtil dbUtil) {
        if (mList.size() < mMaxVolume) {
            mList.add(dbUtil);
        }
    }

    public boolean isFull() {
        return mList.size() >= mMaxVolume ? true : false;
    }

    public DataBaseUtil getInstance() {
        if (mList.size() > 0) {
            for (DataBaseUtil dbUtil : mList) {
                if (dbUtil.isAlive())
                    return dbUtil;
            }
        }
        return null;
    }
}
