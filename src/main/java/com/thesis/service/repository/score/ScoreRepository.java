package com.thesis.service.repository.score;

import com.thesis.service.model.score.ScoreTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends BaseRepository<ScoreTable> {

//   WITH template AS (
// SELECT * FROM sc_template WHERE guide_teacher = TRUE AND thesis = TRUE)

// , score AS (
// SELECT sS.*
// FROM tp_topic tT
// INNER JOIN tp_student tS ON tS.topic_id = tT.id
// LEFT JOIN sc_score sS ON sS.topic_id = tT.id AND sS.student_id = tS.student_id
// WHERE tS.mid_pass = TRUE
// AND sS.teacher_id = 11
// AND sS.guide_teacher = TRUE)

// SELECT template.id AS temId, score.id as scoreId, score.topic_id
// FROM template LEFT JOIN score ON template.id = score.template_id

}
