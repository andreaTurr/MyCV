package com.example.mycv.data

import androidx.compose.ui.res.stringResource
import com.example.mycv.R


val Skills = listOf(
    Skill(R.string.Word, COMPETENCE.FOURSTARS),
    Skill(R.string.C, COMPETENCE.FOURSTARS),
    Skill(R.string.CSharp, COMPETENCE.FOURSTARS),
    Skill(R.string.Java, COMPETENCE.FOURSTARS),
    Skill(R.string.AndroidProgramming, COMPETENCE.FIVESTARS),
    Skill(R.string.GitVersionamento, COMPETENCE.FOURSTARS),
)
val Languages = listOf(
    Skill(R.string.italian, COMPETENCE.FIVESTARS),
    Skill(R.string.english, COMPETENCE.FOURSTARS),
)

val Education = listOf(
    CareerAndEducationItem(title = R.string.unimibTitle, body = R.string.unimibBody, date = R.string.unimibDate, lenght = R.string.unimibDuration),
    CareerAndEducationItem(title = R.string.itisTitle, body = R.string.itisBody, date = R.string.itisDate, lenght = R.string.itisDuration),
)
val Career = listOf(
    CareerAndEducationItem(title = R.string.firstWorkTitle, body = R.string.firstWorkBody, date = R.string.firstWorkDate, lenght = R.string.firstWorkDuration),
    CareerAndEducationItem(title = R.string.stageSecondTitle, body = R.string.stageSecondBody, date = R.string.stageSecondDate, lenght = R.string.stageSecondDuration),
    CareerAndEducationItem(title = R.string.stageFirstTitle, body = R.string.stageFirstBody, date = R.string.stageFirstDate, lenght = R.string.stageFirstDuration),
)



