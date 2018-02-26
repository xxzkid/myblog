package org.xxz.myblog.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.xxz.myblog.common.util.DateUtil;
import org.xxz.myblog.util.MailUtil;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

@Slf4j
@Component
@EnableScheduling
public class BackupTask {

	@Value("${backup.cmd}")
	private String backupCmd;
	@Value("${backup.db}")
	private String backupDb;
	@Value("${backup.dir}")
	private String backupDir;
	@Value("${backup.to}")
	private String backupTo;
	
	@Value("${spring.profiles.active}")
	private String active;
	
	@Autowired
	private MailUtil mailUtil;

	@Scheduled(fixedDelay = 8 * 3600 * 1000)
	public void backup() {
	    
	    if (Objects.equals("dev", active)) {
	        log.debug("the env is {}", active);
	        return ;
	    }

		String now = backupDb + DateUtil.date2String(new Date(), "yyyyMMddHHmmss");
		String sqlPath = backupDir + now + ".sql";
		
		File dirFile = new File(backupDir);
		if (!dirFile.exists() && !dirFile.mkdirs()) {
			log.error("无权限创建文件夹");
			return ;
		}

		String cmd = String.format(backupCmd, backupDb, sqlPath);
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			int processComplete = p.waitFor();
			if (processComplete == 0) {
				log.info("备份完成，开始发送邮件");
				try {
					mailUtil.sendAttachments(backupTo, "数据库备份" + now, "数据库备份" + now, sqlPath);
				} catch (MessagingException e) {
					log.error("发送备份数据邮件失败：" + e.getMessage(), e);
				}
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		}

	}

}
