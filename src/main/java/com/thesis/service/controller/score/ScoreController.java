package com.thesis.service.controller.score;

import com.thesis.service.controller.ABaseController;
import com.thesis.service.model.score.ScoreTable;
import com.thesis.service.service.score.ScoreService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scores")
public class ScoreController extends ABaseController<ScoreTable, ScoreService> {

}
