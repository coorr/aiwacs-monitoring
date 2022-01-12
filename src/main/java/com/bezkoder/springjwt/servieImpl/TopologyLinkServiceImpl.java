package com.bezkoder.springjwt.servieImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.springjwt.service.TopologyLinkService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TopologyLinkServiceImpl implements TopologyLinkService {

}
