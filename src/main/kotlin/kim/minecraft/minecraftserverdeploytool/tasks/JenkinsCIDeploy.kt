package kim.minecraft.minecraftserverdeploytool.tasks

import kim.minecraft.minecraftserverdeploytool.utils.JenkinsCIUtil

interface JenkinsCIDeploy : Task {
    val baseLink: String
    val author: String
    val repo: String
    val name: String
    val buildID: String?
    val saveDir: String
    val fileName: String?
    val index:Int?
    override fun run(): String {
        val ci = JenkinsCIUtil(baseLink, author, repo)
        val ind = index ?: 0
        val buildID = buildID ?: ci.getLatestBuild()
        val fileName = fileName ?: ci.getFileName(ind, buildID)
        println("开始下载$name，构建版本$buildID")
        ci.download(ind, buildID, saveDir, fileName)
        println("下载完成")
        additionAction()
        return fileName
    }

    fun additionAction() {}
}