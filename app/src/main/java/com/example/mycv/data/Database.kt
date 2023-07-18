package com.example.mycv.data

import com.example.mycv.R


val Skills = listOf(
    Skill("Word", COMPETENCE.FOURSTARS),
    Skill("C", COMPETENCE.FOURSTARS),
    Skill("C#", COMPETENCE.FOURSTARS),
    Skill("Java", COMPETENCE.FOURSTARS),
    Skill("Programmazione Android", COMPETENCE.FIVESTARS),
    Skill("Versionamento(Git)", COMPETENCE.FOURSTARS),
)
val Languages = listOf(
    Skill("Italian", COMPETENCE.FIVESTARS),
    Skill("English", COMPETENCE.FOURSTARS),
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



