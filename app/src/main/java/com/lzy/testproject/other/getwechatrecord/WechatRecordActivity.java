package com.lzy.testproject.other.getwechatrecord;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.lzy.testproject.R;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteDatabaseHook;

import java.io.File;

import static android.database.sqlite.SQLiteDatabase.NO_LOCALIZED_COLLATORS;
import static net.sqlcipher.database.SQLiteDatabase.openDatabase;

public class WechatRecordActivity extends AppCompatActivity {

	String parentPath = "/data/data/com.tencent.mm/MicroMsg/";

	//	String oldPath = "/data/data/com.tencent.mm/MicroMsg/54466d9340acc4a839b45831dadd8dc0/EnMicroMsg.db";
	String newPath = "/data/data/com.lzy.testproject/EnMicroMsg.db";
//	String uin = "669000982";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wechat_record);

		new MyThread().start();
	}

	class MyThread extends Thread {
		@Override
		public void run() {
			super.run();
			String imei = Utils.getIMEI(WechatRecordActivity.this);
			try {
				String uin = Utils.parseXmlForUin();
				String msgPath = parentPath + Utils.md5("mm" + uin).toLowerCase() + File.separator + "EnMicroMsg.db";
				String password = (Utils.md5(imei + uin).substring(0, 7).toLowerCase());
				Utils.copyFile(msgPath, newPath);
				SQLiteDatabase.loadLibs(WechatRecordActivity.this);
				SQLiteDatabaseHook hook = new SQLiteDatabaseHook() {
					public void preKey(SQLiteDatabase database) {
					}

					public void postKey(SQLiteDatabase database) {
						database.rawExecSQL("PRAGMA cipher_migrate;");//很重要
					}
				};
				SQLiteDatabase db = openDatabase(newPath, password, null, NO_LOCALIZED_COLLATORS, hook);
				String sql = "select * from message";
				Log.e("sql", sql);
				Cursor c = db.rawQuery(sql, null);
				for (int i = 0; i < c.getColumnCount(); i++) {
					Log.e("lzy", "columnName:" + c.getColumnName(i));
				}
				while (c.moveToNext()) {
					long msgId = c.getLong(c.getColumnIndex("msgId"));
					String content = c.getString(c.getColumnIndex("content"));
					int type = c.getInt(c.getColumnIndex("type"));
					String talker = c.getString(c.getColumnIndex("talker"));
					long createTime = c.getLong(c.getColumnIndex("createTime"));

					Log.e("lzy", "msgId:" + msgId + "\ncontent:" + content + "\ntype:" + type + "\ntalker:" + talker + "\ncreateTime:" + createTime);
//				JSONObject tmpJson = handleJson(_id, content, type, talker, time);
//				returnJson.put("data" + count, tmpJson);
				}
				c.close();
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
