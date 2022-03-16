from django.contrib.auth import authenticate
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
from .models import MyUser
from django.core.files.storage import default_storage
from django.core.files.base import ContentFile
import yolo
import pymysql


@csrf_exempt
def join(request):
    if request.method == 'POST':
        username = request.POST.get('username', '')
        password = request.POST.get('password', '')
        age = request.POST.get('age', 0)
        sex = request.POST.get('sex', '')
        weight = request.POST.get('weight', 0)
        user = MyUser.objects.create_superuser(username, password, age, sex, weight)
        user.save()
        return JsonResponse({'code': '0000', 'msg': '회원가입 성공입니다.'}, status=200)


@csrf_exempt
def login(request):
    if request.method == 'POST':
        id = request.POST.get('userid', '')
        pw = request.POST.get('userpw', '')

        result = authenticate(username=id, password=pw)

        if result:
            print("성공")
            return JsonResponse({'code': '0000', 'msg': '로그인성공입니다.'}, status=200)
        else:
            print("실패")
            return JsonResponse({'code': '1001', 'msg': '로그인실패입니다.'}, status=200)


@csrf_exempt
def calculate(request):
    if request.method == 'POST':
        file = request.FILES['proFile']
        default_storage.save(str(file), ContentFile(file.read()))
        date = request.POST.get('date', '')
        user = request.POST.get('id', '')
        sum_calorie, img = yolo.process(str(file), user, date)
        return JsonResponse({'code': '0000', 'msg': str(sum_calorie), 'img': img},  status=200)


@csrf_exempt
def food(request):
    if request.method == 'POST':
        db = pymysql.connect(
            user='root',
            passwd='1234',
            host='localhost',
            db='food',
            charset='utf8'
        )
        cursor = db.cursor()

        time = request.POST.get('time', '')
        userid = request.POST.get('id', '')
        sql = "select food_name, tim, calorie, carbo, protein, fat from user_food where user = %s and tim like %s"
        cursor.execute(sql, (userid, "%"+time))
        result = cursor.fetchall()
        print(result)
        if len(result) == 0:
            return JsonResponse({"code": "0001"})
        else:
            lst = [list(foods) for foods in result]
            print(lst)
            return JsonResponse({"foods": [list(foods) for foods in result], "code": "0000"}, status=200)
