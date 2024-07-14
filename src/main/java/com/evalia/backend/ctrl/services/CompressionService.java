package com.evalia.backend.ctrl.services;

import java.io.InputStream;
import java.io.OutputStream;

public interface CompressionService {

	public OutputStream compressFile(InputStream inStream);
	
}
