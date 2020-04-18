package com.yhzn.model.common;

/**
 * 通用编号类
 * @author liany
 *
 */
public class SysSequence extends SysSequenceModel{

	//seq的序号长度
	private int seqLength = 3;

	public int getSeqLength() {
		return seqLength;
	}

	public void setSeqLength(int seqLength) {
		this.seqLength = seqLength;
	}
}
