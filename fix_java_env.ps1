# Java环境变量修复脚本
# 需要以管理员权限运行

Write-Host "正在修复Java环境变量..." -ForegroundColor Green

# 设置JAVA_HOME
$javaHome = "C:\Program Files\Java\jdk-17.0.1"
[Environment]::SetEnvironmentVariable("JAVA_HOME", $javaHome, "Machine")

Write-Host "已设置JAVA_HOME为: $javaHome" -ForegroundColor Yellow

# 获取当前PATH
$currentPath = [Environment]::GetEnvironmentVariable("PATH", "Machine")

# 移除所有Java相关路径
$pathArray = $currentPath -split ';'
$filteredPath = $pathArray | Where-Object { 
    $_ -notlike '*java*' -and 
    $_ -notlike '*Java*' -and 
    $_ -notlike '*jdk*' -and 
    $_ -notlike '*jre*'
}

# 添加Java 17路径到最前面
$newPath = "$javaHome\bin;" + ($filteredPath -join ';')

# 设置新的PATH
[Environment]::SetEnvironmentVariable("PATH", $newPath, "Machine")

Write-Host "已更新PATH环境变量" -ForegroundColor Yellow
Write-Host "Java 17路径已添加到PATH最前面" -ForegroundColor Green

Write-Host "`n修复完成！请重新打开命令提示符或PowerShell以应用更改。" -ForegroundColor Green
Write-Host "验证命令:" -ForegroundColor Cyan
Write-Host "  java -version" -ForegroundColor White
Write-Host "  javac -version" -ForegroundColor White
Write-Host "  echo %JAVA_HOME%" -ForegroundColor White 