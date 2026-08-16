package com.example.data

data class SkillCategory(
    val id: String,
    val title: String,
    val iconName: String,
    val skills: List<SkillItem>
)

data class SkillItem(
    val name: String,
    val description: String,
    val proficiency: Int, // 1 to 100
    val level: String = "Expert"
)

data class WorkExperience(
    val id: String,
    val role: String,
    val company: String,
    val period: String,
    val location: String = "Hyderabad, India",
    val keyAchievements: List<String>,
    val highlights: List<String>,
    val description: String
)

data class FeaturedProject(
    val id: String,
    val title: String,
    val category: String,
    val summary: String,
    val impact: String,
    val tools: List<String>,
    val methodology: String,
    val metrics: List<Pair<String, String>>
)

data class EducationItem(
    val degree: String,
    val institution: String,
    val year: String,
    val details: String,
    val badge: String
)

data class CertificationItem(
    val title: String,
    val issuer: String,
    val year: String,
    val tag: String,
    val verified: Boolean = true
)

object PortfolioData {
    const val NAME = "Sonia Saxena"
    const val TITLE = "Business Analyst | Strategy, AI Integration & Data Analytics"
    const val LOCATION = "Hyderabad, Telangana"
    const val EMAIL = "sonia.s2806@gmail.com"
    const val PHONE = "+91 7893595088"
    const val LINKEDIN = "https://linkedin.com/in/soniasaxena"
    const val GITHUB = "https://github.com/soniasaxena"
    const val TAGLINE = "Results-oriented Business Analyst with nearly 10 years of experience translating strategic objectives into structured analytical frameworks, driving revenue growth, and leveraging generative AI & autonomous agents."

    val STATS = listOf(
        Pair("10+ Years", "Strategic & BA Experience"),
        Pair("₹70 Lakhs", "Delivered (Target ₹60L)"),
        Pair("7 Consecutive", "Club 90 Awards (Kotak)"),
        Pair("12 Members", "Cross-Functional Team Led"),
        Pair("5 Gold", "Key Member Accounts Built")
    )

    val SKILL_CATEGORIES = listOf(
        SkillCategory(
            id = "ba",
            title = "BA Techniques",
            iconName = "analytics",
            skills = listOf(
                SkillItem("Requirements Elicitation", "Deep stakeholder interviews, workshops, and user stories", 95),
                SkillItem("Stakeholder Management", "Executive briefing, cross-functional alignment & buy-in", 94),
                SkillItem("Business Process Analysis", "As-Is / To-Be process mapping, workflow streamlining", 92),
                SkillItem("Gap Analysis", "Operational friction identification & root cause discovery", 90),
                SkillItem("Performance Reporting", "Actionable KPI scorecards & executive dashboards", 93)
            )
        ),
        SkillCategory(
            id = "strategy",
            title = "Strategy & Planning",
            iconName = "strategy",
            skills = listOf(
                SkillItem("Strategic Planning", "Market feasibility studies & growth roadmap design", 92),
                SkillItem("Business Development", "High-value partner acquisition & revenue scaling", 94),
                SkillItem("Distribution Management", "Agency channel governance & branch performance", 90),
                SkillItem("Sales & Marketing Strategy", "Go-To-Market orchestration & lead funnels", 91)
            )
        ),
        SkillCategory(
            id = "data",
            title = "Data & Visualisation",
            iconName = "data",
            skills = listOf(
                SkillItem("Data Analysis & Visualisation", "Exploratory data analytics & trend modeling", 90),
                SkillItem("Tableau", "Interactive BI dashboards, cohort analysis & forecasting", 88),
                SkillItem("Python (Data Analysis)", "Pandas, NumPy, automated data wrangling scripts", 85),
                SkillItem("Mathematical Optimisation", "Resource allocation & operational modeling", 84)
            )
        ),
        SkillCategory(
            id = "ai",
            title = "AI Tools & Automation",
            iconName = "ai",
            skills = listOf(
                SkillItem("Generative AI Concepts", "LLM architectures, multi-modal workflows & reasoning", 92),
                SkillItem("AI Agents & Autonomous Systems", "Tool-augmented agents, task decomposition & RAG", 89),
                SkillItem("Prompt Engineering", "Structured system prompting, few-shot conditioning", 94),
                SkillItem("AI Productivity Tools", "Workflow automation, synthetic data & code accelerators", 90)
            )
        ),
        SkillCategory(
            id = "methodologies",
            title = "Methodologies & CRM",
            iconName = "agile",
            skills = listOf(
                SkillItem("Agile & Scrum", "Sprint planning, backlog refinement & daily standups", 90),
                SkillItem("4DX Execution Framework", "Wildly Important Goals (WIG) & lead measure tracking", 96),
                SkillItem("CRM Systems", "Customer lifecycle tracking, conversion funnels & retention", 92),
                SkillItem("Direct Marketing", "Targeted customer engagement & campaign analytics", 88)
            )
        )
    )

    val EXPERIENCES = listOf(
        WorkExperience(
            id = "exp_1",
            role = "Territory Manager",
            company = "Reliance Nippon Life Insurance",
            period = "Oct 2022 – Mar 2024",
            location = "Hyderabad, India",
            keyAchievements = listOf(
                "Led a high-performing team of 12 professionals across branch operations.",
                "Exceeded annual target of ₹60 Lakhs by delivering ₹70 Lakhs in high-margin renewals and new acquisitions.",
                "Created and nurtured 5 Gold Member accounts through executive relationship management and tailored consultative strategies.",
                "Instituted data-driven weekly performance cadence to accelerate advisor productivity."
            ),
            highlights = listOf("Team of 12", "₹70L Achieved (117% Target)", "5 Gold Accounts", "Territory Leadership"),
            description = "Spearheaded territory sales, partner distribution governance, and cross-functional analytics for life insurance portfolios. Formulated regional market strategy and led end-to-end sales execution."
        ),
        WorkExperience(
            id = "exp_2",
            role = "Assistant Branch Manager",
            company = "Kotak Life Insurance",
            period = "2014 – Aug 2022",
            location = "Hyderabad / Regional Branch",
            keyAchievements = listOf(
                "Implemented the 4DX (4 Disciplines of Execution) framework, systematically tracking lead measures and daily scorecards.",
                "Honored with the prestigious Club 90 Award for 7 consecutive years for sustained top-tier revenue and policy retention.",
                "Recruited, mentored, and coached multiple MDRT (Million Dollar Round Table) qualified advisors.",
                "Earned 3 merit-based internal promotions over an 8-year tenure demonstrating executive leadership and operational excellence."
            ),
            highlights = listOf("7x Club 90 Award", "4DX Execution Pioneer", "MDRT Advisor Coach", "3 Promotions"),
            description = "Drove agency distribution, operational compliance, and advisor capability building. Developed rigorous branch reporting mechanisms and fostered an achievement-oriented sales culture."
        ),
        WorkExperience(
            id = "exp_3",
            role = "Sales Manager / Corporate Trainer",
            company = "Appco Group",
            period = "2008 – 2011",
            location = "India",
            keyAchievements = listOf(
                "Fast-tracked and promoted to Corporate Trainer, leading training modules for over 50+ sales associates.",
                "Received the 'Best Sales Manager Award' for exemplary client acquisition and campaign conversion metrics.",
                "Selected and qualified for the prestigious KONFLUENCE Leadership Programme for exceptional strategic aptitude."
            ),
            highlights = listOf("Corporate Trainer", "Best Sales Manager", "KONFLUENCE Leader", "Sales Coaching"),
            description = "Delivered direct marketing initiatives, designed sales training curricula, and oversaw campaign performance analytics across corporate clients."
        )
    )

    val FEATURED_PROJECTS = listOf(
        FeaturedProject(
            id = "proj_1",
            title = "Sales Performance Tracking Framework",
            category = "Business Analytics & BI",
            summary = "Designed a comprehensive KPI taxonomy and structured review cadences to improve renewal conversion rates and advisor accountability.",
            impact = "Accelerated team target fulfillment to 117% (₹70L delivered) and cut pipeline review turnaround time by 40%.",
            tools = listOf("Tableau", "KPI Taxonomy", "Gap Analysis", "Stakeholder Alignment"),
            methodology = "Structured Requirements Elicitation + 4DX Scoreboards",
            metrics = listOf(
                Pair("117%", "Target Delivery"),
                Pair("40%", "Faster Reviews"),
                Pair("12 Advisors", "Standardized Reporting")
            )
        ),
        FeaturedProject(
            id = "proj_2",
            title = "Distribution Network Expansion",
            category = "Strategic Market Development",
            summary = "Conducted rigorous channel analysis and geographic gap analysis to profile and onboard reliable agency partners across Hyderabad.",
            impact = "Established 5 premier Gold Member partner accounts and expanded geographic territory penetration by 28%.",
            tools = listOf("Channel Strategy", "Distribution Mapping", "Partner Profiling", "CRM"),
            methodology = "Market Gap Analysis & Stakeholder Relationship Management",
            metrics = listOf(
                Pair("5 Accounts", "Gold Member Onboarded"),
                Pair("+28%", "Territory Reach"),
                Pair("100%", "Compliance Rate")
            )
        ),
        FeaturedProject(
            id = "proj_3",
            title = "Team Capability Development",
            category = "Organizational Agility & Enablement",
            summary = "Designed custom training curricula to bridge execution gaps, mentor advisors on MDRT standards, and boost daily productivity.",
            impact = "Coached multiple MDRT-qualifying advisors and sustained a 7-year continuous streak of top-tier Club 90 branch recognitions.",
            tools = listOf("4DX Framework", "Scrum Methodologies", "Curriculum Design", "Coaching"),
            methodology = "Agile Coaching & Lead-Measure Execution",
            metrics = listOf(
                Pair("7 Years", "Consecutive Club 90"),
                Pair("MDRT", "Advisor Qualification"),
                Pair("50+", "Associates Trained")
            )
        )
    )

    val EDUCATION = listOf(
        EducationItem(
            degree = "Master's Program — AI-Powered Business Analyst",
            institution = "Simplilearn in collaboration with IBM",
            year = "2026",
            details = "Advanced enterprise business analysis, predictive data analytics, prompt engineering, and GenAI integration for enterprise workflows.",
            badge = "Executive Master's"
        ),
        EducationItem(
            degree = "AI Career Accelerator Program",
            institution = "be10x in collaboration with IIT Kharagpur",
            year = "2026",
            details = "Autonomous AI agents, generative AI tools, prompt architecture, and enterprise AI workflow automation.",
            badge = "Advanced AI"
        ),
        EducationItem(
            degree = "Master of Business Administration (MBA - Marketing & IT)",
            institution = "ICMHRD — Balaji Society, Pune",
            year = "2006 – 2008",
            details = "Dual specialization in Marketing Management & Information Technology. Focus on strategic business systems and marketing strategy.",
            badge = "MBA"
        ),
        EducationItem(
            degree = "Bachelor of Commerce (B.Com)",
            institution = "Bhopal School of Social Science",
            year = "2003 – 2006",
            details = "Foundational studies in Financial Accounting, Economics, Business Law, and Quantitative Analysis.",
            badge = "B.Com"
        )
    )

    val CERTIFICATIONS = listOf(
        CertificationItem("CBAP® Training", "International Institute of Business Analysis (IIBA)", "2026", "Business Analysis"),
        CertificationItem("Tableau Desktop Specialist", "Tableau / Salesforce", "2026", "Data Visualisation"),
        CertificationItem("AI Generalist & Prompt Engineering", "be10x / IIT Kharagpur", "2026", "Artificial Intelligence"),
        CertificationItem("AI Product Building & Autonomous Systems", "Simplilearn / IBM", "2026", "AI Integration"),
        CertificationItem("4DX Execution Practitioner", "FranklinCovey / Corporate", "2020", "Methodologies"),
        CertificationItem("Club 90 Excellence (7x Consecutive)", "Kotak Life Insurance", "2015-2022", "Executive Sales Award")
    )
}
