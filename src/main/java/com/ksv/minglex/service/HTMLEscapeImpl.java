package com.ksv.minglex.service;

import org.springframework.stereotype.Service;

@Service("XSSPreventionService")
public class HTMLEscapeImpl implements XSSPreventionService {
	@Override
	public String filter(String s) {
		// TODO Auto-generated method stub
		StringBuilder out = new StringBuilder(Math.max(16, s.length()));
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c > 127 || c == '"' || c == '<' || c == '>' || c == '&' || c == '/' || c == 39) {
				out.append("&#");
				out.append((int) c);
				out.append(';');
			} else {
				out.append(c);
			}
		}
		return out.toString();
	}
}
