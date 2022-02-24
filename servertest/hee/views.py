from django.contrib.auth import authenticate
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
from .models import MyUser
from django.core.files.storage import default_storage
from django.core.files.base import ContentFile
import yolo


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

        sum_calorie, img = yolo.process(str(file))
        return JsonResponse({'code': '0000', 'msg': str(sum_calorie), 'img': img},  status=200)
