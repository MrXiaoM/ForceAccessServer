var ASMAPI = Java.type('net.minecraftforge.coremod.api.ASMAPI');
var Opcodes = Java.type('org.objectweb.asm.Opcodes');
var InsnNode = Java.type('org.objectweb.asm.tree.InsnNode');
var TypeInsnNode = Java.type('org.objectweb.asm.tree.TypeInsnNode');
var MethodInsnNode = Java.type('org.objectweb.asm.tree.MethodInsnNode');

function initializeCoreMod() {
    info('ForceAccessServer ASM transformer loaded!');
    return {
        'CreateSocialInteractionsTransformer': {
            'target': {
                'type': 'CLASS',
                'name': 'net/minecraft/client/Minecraft'
            },
            'transformer': function (cn) {
                var authlib318 = false;
                cn.methods.forEach(function (method) {
                    if (method.desc.endsWith('Lcom/mojang/authlib/minecraft/BanDetails;')) {
                        authlib318 = true;
                    }
                });
                cn.methods.forEach(function (mn) {
                    // authlib 2
                    if (mn.desc.endsWith('Lcom/mojang/authlib/minecraft/SocialInteractionsService;')) {
                        info('ForceAccessServer Mod Injected authlib 2 (' + cn.name + '.' + mn.name + ')');
                        var node = mn.instructions.get(0);
                        mn.instructions.insertBefore(node, new TypeInsnNode(Opcodes.NEW, 'top/mrxiaom/fas/UnlimitedSocialInteractions2'));
                        mn.instructions.insertBefore(node, new InsnNode(Opcodes.DUP));
                        mn.instructions.insertBefore(node, new MethodInsnNode(Opcodes.INVOKESPECIAL, 'top/mrxiaom/fas/UnlimitedSocialInteractions2', '<init>', '()V', false));
                        mn.instructions.insertBefore(node, new InsnNode(Opcodes.ARETURN));
                    }
                    // authlib 3.3/3.18 (1.18+/1.19+)
                    if (mn.desc.endsWith('Lcom/mojang/authlib/minecraft/UserApiService;')) {
                        if (authlib318) {
                            info('ForceAccessServer Mod Injected authlib 3.18 (' + cn.name + '.' + mn.name + ')');
                            var node = mn.instructions.get(0);
                            mn.instructions.insertBefore(node, new TypeInsnNode(Opcodes.NEW, 'top/mrxiaom/fas/UnlimitedSocialInteractions3_18'));
                            mn.instructions.insertBefore(node, new InsnNode(Opcodes.DUP));
                            mn.instructions.insertBefore(node, new MethodInsnNode(Opcodes.INVOKESPECIAL, 'top/mrxiaom/fas/UnlimitedSocialInteractions3_18', '<init>', '()V', false));
                            mn.instructions.insertBefore(node, new InsnNode(Opcodes.ARETURN));
                        } else {
                            info('ForceAccessServer Mod Injected authlib 3.3 (' + cn.name + '.' + mn.name + ')');
                            var node = mn.instructions.get(0);
                            mn.instructions.insertBefore(node, new TypeInsnNode(Opcodes.NEW, 'top/mrxiaom/fas/UnlimitedSocialInteractions3_3'));
                            mn.instructions.insertBefore(node, new InsnNode(Opcodes.DUP));
                            mn.instructions.insertBefore(node, new MethodInsnNode(Opcodes.INVOKESPECIAL, 'top/mrxiaom/fas/UnlimitedSocialInteractions3_3', '<init>', '()V', false));
                            mn.instructions.insertBefore(node, new InsnNode(Opcodes.ARETURN));
                        }
                    }

                    // TODO authlib 4 (1.20+)

                });
                return cn;
            }
        }
    };
}

function info(s) {
    ASMAPI.log('INFO', s)
}