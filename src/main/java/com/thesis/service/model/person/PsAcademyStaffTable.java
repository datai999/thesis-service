package com.thesis.service.model.person;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.thesis.service.model.PersonBaseTable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ps_academy_staff")
public class PsAcademyStaffTable extends PersonBaseTable {
}
