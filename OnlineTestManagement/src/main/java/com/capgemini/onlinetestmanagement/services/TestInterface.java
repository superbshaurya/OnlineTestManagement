package com.capgemini.onlinetestmanagement.services;

import java.util.List;

import com.capgemini.onlinetestmanagement.entity.Test1;

public interface TestInterface {

	public boolean addtest(Test1 test);
	public List<Test1>show();
	public void deletetest(int testId);
	public Test1  updatetest(Test1 test,int testId);
}
