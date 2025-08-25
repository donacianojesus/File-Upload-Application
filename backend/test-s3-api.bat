@echo off
echo === Testing S3 API Endpoints ===
echo.

echo Testing bucket creation...
set bucketName=test-bucket-%random%
echo Creating bucket: %bucketName%

curl -X POST "http://localhost:8080/api/s3/bucket?bucketName=%bucketName%"

echo.
echo.
echo Testing bucket existence check...
curl "http://localhost:8080/api/s3/bucket/%bucketName%/exists"

echo.
echo.
echo === Test Complete ===
pause
