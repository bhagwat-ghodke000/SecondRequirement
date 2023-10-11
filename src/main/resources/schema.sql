CREATE TABLE If NOT EXISTS `user` (
  `user_id` bigint NOT NULL,
  `aadhar_card_number` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `confirm_password` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `email_id` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `pan_card_number` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `pin_code` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `user_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_hkqqbhgj3sgpk4u55kb6orcmq` (`aadhar_card_number`),
  UNIQUE KEY `UK_r9kvst217faqa7vgeyy51oos0` (`email_id`),
  UNIQUE KEY `UK_lt2b8ocin3hdnm9q6fm4o8xnr` (`mobile_number`),
  UNIQUE KEY `UK_4wrp9y9k5qioqek87vqy6kp12` (`pan_card_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




CREATE TABLE If NOT EXISTS `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE If NOT EXISTS `role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE If NOT EXISTS`user_information` (
  `user_id_information_id` bigint NOT NULL AUTO_INCREMENT,
  `ctc` varchar(255) DEFAULT NULL,
  `achievements` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `exist_date` datetime(6) DEFAULT NULL,
  `joining_date` datetime(6) DEFAULT NULL,
  `uploadcv` varchar(255) DEFAULT NULL,
  `user_user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`user_id_information_id`),
  KEY `FK5h3gts78mas2ed5d8w7qpwbp6` (`user_user_id`),
  CONSTRAINT `FK5h3gts78mas2ed5d8w7qpwbp6` FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE If NOT EXISTS`user_roles` (
  `user_user_id` bigint NOT NULL,
  `roles_role_id` bigint NOT NULL,
  PRIMARY KEY (`user_user_id`,`roles_role_id`),
  KEY `FKhxmmg8j4h4qpwbvf39cnujlkf` (`roles_role_id`),
  CONSTRAINT `FKhxmmg8j4h4qpwbvf39cnujlkf` FOREIGN KEY (`roles_role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `FKkv46dn3qakjvsk7ra33nd5sns` FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;