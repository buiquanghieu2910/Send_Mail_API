/**
 * DATN_FALL2022, 2022
 * BatchConfiguration.java, BUI_QUANG_HIEU
 */
package com.hieubq;

import java.util.Date;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author BUI_QUANG_HIEU
 *
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	/**
	 * Mỗi phút chạy 1 lần từ thứ 2 đến chủ nhật
	 */
	@Scheduled(cron = "0 * * * * MON-SUN")
	public void name() {
		System.out.println("Hiếu "+ new Date());
	}
}
