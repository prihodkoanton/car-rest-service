package com.foxminded.aprihodko.carrestservice.model.search;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum FieldType {

	BOOLEAN {
		@Override
		public Object parse(String value) {
			return Boolean.valueOf(value);
		}
	},

	CHAR {
		@Override
		public Object parse(String value) {
			return value.charAt(0);
		}
	},

	DOUBLE {
		@Override
		public Object parse(String value) {
			return Double.valueOf(value);
		}
	},
	INTEGER {
		@Override
		public Object parse(String value) {
			return Integer.valueOf(value);
		}
	},
	LONG {
		@Override
		public Object parse(String value) {
			return Long.valueOf(value);
		}
	},
	STRING {
		@Override
		public Object parse(String value) {
			return value;
		}
	};

	public abstract Object parse(String value);
}
